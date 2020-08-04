package cc.caker.springboot.service.impl;

import cc.caker.springboot.repo.mapper.db2.MenuMapper;
import cc.caker.springboot.repo.model.db2.Menu;
import cc.caker.springboot.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cakeralter
 * @date 2020/8/4
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
