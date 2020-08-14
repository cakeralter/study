package cc.caker.boot.module.um.service.impl;

import cc.caker.boot.component.CustomProperties;
import cc.caker.boot.constant.Enumerations;
import cc.caker.boot.constant.Enumerations.Status;
import cc.caker.boot.constant.Enumerations.UserType;
import cc.caker.boot.module.um.service.AdminService;
import cc.caker.boot.repo.mapper.db1.AdminMapper;
import cc.caker.boot.repo.mapper.db1.AdminRoleMapper;
import cc.caker.boot.repo.mapper.db1.RoleMapper;
import cc.caker.boot.repo.model.db1.Admin;
import cc.caker.boot.repo.model.db1.AdminRole;
import cc.caker.boot.repo.model.db1.Role;
import cc.caker.boot.util.BaseUtils;
import cc.caker.boot.util.EncryptUtils;
import cc.caker.common.service.RedisService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

import static cc.caker.boot.constant.RedisConst.GL_VERIFY_CODE;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    private final CustomProperties properties;
    private final RedisService redisService;
    private final AdminMapper adminMapper;
    private final AdminRoleMapper adminRoleMapper;
    private final RoleMapper roleMapper;

    @Override
    public int delete(Integer... ids) {
        int count = 0;
        Admin admin = new Admin();
        admin.setStatus(Enumerations.Status.DISABLED.getValue());
        for (Integer id : ids) {
            admin.setId(id);
            count += adminMapper.updateById(admin);
        }
        return count;
    }

    @Override
    public boolean register(Admin admin, String input) {
        String key = GL_VERIFY_CODE + "::" + admin.getUsername();
        String code = redisService.get(key);
        if (Objects.isNull(code) || !code.equalsIgnoreCase(input)) throw new RuntimeException("验证码不正确");
        String salt = BaseUtils.randomCode(properties.getSaltLength());
        admin.setPassword(EncryptUtils.encrypt(admin.getPassword(), salt));
        // 盐作为私钥
        admin.setSecret(salt);
        // 默认昵称为用户名
        admin.setNickName(admin.getUsername());
        // 序号
        int sort = Optional.ofNullable(adminMapper.findMaxSort()).map(x -> ++x).orElse(1);
        admin.setSort(sort);
        if (adminMapper.insert(admin) > 0) {
            // 授予默认用户权限
            Role role = new Role();
            role.setCode(UserType.USER.name());
            role.setStatus(Status.ENABLED.getValue());
            Role origin = roleMapper.selectOne(new QueryWrapper<>(role));
            if (Objects.nonNull(origin)) {
                AdminRole ar = new AdminRole();
                ar.setAdminId(admin.getId());
                ar.setRoleId(origin.getId());
                adminRoleMapper.insert(ar);
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean changePassword(String adminId, String password) {
        Admin origin = adminMapper.selectById(adminId);
        if (Objects.isNull(origin)) {
            return false;
        }
        origin.setPassword(EncryptUtils.encrypt(password, origin.getSecret()));
        return adminMapper.updateById(origin) > 0;
    }
}
