package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db2.LogMapper;
import cc.caker.springboot.repo.model.db2.Log;
import cc.caker.springboot.service.LogService;
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
