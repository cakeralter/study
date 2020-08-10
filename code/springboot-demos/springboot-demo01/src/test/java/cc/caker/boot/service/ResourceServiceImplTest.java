package cc.caker.boot.service;

import cc.caker.boot.module.um.service.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author cakeralter
 * @date 2020/8/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServiceImplTest {

    @Autowired
    private ResourceService resourceService;

    @Test
    public void testLoadAllResources() {
        resourceService.loadAllResources();
    }
}
