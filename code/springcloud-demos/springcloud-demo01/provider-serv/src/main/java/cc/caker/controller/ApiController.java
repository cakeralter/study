package cc.caker.controller;

import cc.caker.api.ApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cakeralter
 * @date 2020/8/3
 */
@RefreshScope
@RestController("/api")
public class ApiController implements ApiService {

    @Value("${name:wangm}")
    private String name;


    @Override
    public String say(@PathVariable("msg") String msg) {
        System.out.println("【---------from---------】" + msg);
        return "Hello Vegetable Chicken!";
    }

    @GetMapping("/config")
    public String config() {
        return name;
    }
}
