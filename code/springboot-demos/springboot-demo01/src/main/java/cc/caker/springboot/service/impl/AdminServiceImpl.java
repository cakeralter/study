package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db1.AdminMapper;
import cc.caker.springboot.repo.model.db1.Admin;
import cc.caker.springboot.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

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
    public int delete(Integer[] ids) {
        return adminMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
