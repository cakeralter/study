package cc.caker.study.spring.beans;

import lombok.extern.slf4j.Slf4j;

/**
 * UserService
 *
 * @author cakeralter
 * @date 2022/4/7
 * @since 1.0
 */
@Slf4j
public class UserService {

    /**
     * printUserInfo
     *
     * @param username
     */
    public void printUserInfo(String username) {
        log.info("User[username={}, sex=18]", username);
    }
}
