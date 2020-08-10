package cc.caker.boot.module.um.service.impl;

import cc.caker.boot.constant.Enumerations;
import cc.caker.boot.module.um.service.AdminService;
import cc.caker.boot.repo.mapper.db1.AdminMapper;
import cc.caker.boot.repo.model.db1.Admin;
import cc.caker.boot.util.EncryptUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@RequiredArgsConstructor
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    private final AdminMapper adminMapper;

    @Transactional(rollbackFor = Exception.class)
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
    public boolean changePassword(String adminId, String password) {
        Admin origin = adminMapper.selectById(adminId);
        if (Objects.isNull(origin)) {
            return false;
        }
        origin.setPassword(EncryptUtils.encrypt(password, origin.getSecret()));
        return adminMapper.updateById(origin) > 0;
    }
}
