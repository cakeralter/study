package cc.caker.boot.module.log.service.impl;

import cc.caker.boot.module.log.service.LogService;
import cc.caker.boot.repo.mapper.db2.LogMapper;
import cc.caker.boot.repo.model.db2.Log;
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
