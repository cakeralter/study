package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db1.AdminResourceMapper;
import cc.caker.springboot.repo.model.db1.AdminResource;
import cc.caker.springboot.service.AdminResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author cakeralter
 * @date 2020/8/6
 */
@Service
public class AdminResourceServiceImpl extends ServiceImpl<AdminResourceMapper, AdminResource> implements AdminResourceService {
}
