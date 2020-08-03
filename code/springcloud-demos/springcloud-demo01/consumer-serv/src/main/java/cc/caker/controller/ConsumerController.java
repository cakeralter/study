package cc.caker.controller;

import cc.caker.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cakeralter
 * @date 2020/8/3
 */
@RestController
public class ConsumerController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/say")
    public String say() {
        return apiService.say("Hello Nacos!");
    }
}
