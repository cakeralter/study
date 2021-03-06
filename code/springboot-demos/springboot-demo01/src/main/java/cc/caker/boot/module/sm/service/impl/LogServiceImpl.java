package cc.caker.boot.module.sm.service.impl;

import cc.caker.boot.module.sm.service.LogService;
import cc.caker.boot.repo.mapper.db1.LogMapper;
import cc.caker.boot.repo.model.db1.Log;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author cakeralter
 * @since 2020-08-06
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
}
