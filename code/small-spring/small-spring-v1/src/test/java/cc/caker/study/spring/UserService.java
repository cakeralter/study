package cc.caker.study.spring;

/**
 * UserService
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public class UserService {

    /**
     * getByUser
     *
     * @param username
     * @return
     */
    public String getByName(String username) {
        return username + "[age = 18, sex = 女]";
    }
}
