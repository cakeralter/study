package cc.caker.study.spring.service;

import cc.caker.study.spring.stereotype.Component;
import lombok.Data;

import java.util.Random;

/**
 * UserServiceImpl
 *
 * @author cakeralter
 * @date 2022/4/27
 * @since 1.0
 */
@Component("userService")
@Data
public class UserServiceImpl implements UserService {

    private String token;


    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "luqian: " + token;
    }
}
