package cc.caker.boot.constant;

/**
 * @author cakeralter
 * @date 2020/8/7
 */
public interface RedisConst {

    /**
     * 默认key有效时间(s)
     */
    long DEFAULT_KEY_EXPIRE = 30 * 60 * 1000;
    /**
     * 默认验证码key有效时间(s)
     */
    long DEFAULT_VERIFY_CODE_KEY_EXPIRE = 10 * 60 * 1000;
    /**
     * 验证码
     */
    String GL_VERIFY_CODE = "GL_REGISTER_VERIFY_CODE";
    /**
     * 用户角色
     */
    String UM_ADMIN_ROLE = "UM_ADMIN_ROLE";
    /**
     * 角色资源
     */
    String UM_ROLE_RESOURCE = "UM_ROLE_RESOURCE";
    /**
     * 用户资源
     */
    String UM_ADMIN_RESOURCE = "UM_ADMIN_RESOURCE";
    /**
     * 所有资源
     */
    String UM_RESOURCES_ALL = "UM_RESOURCES_ALL";
    /**
     * 所有可用资源
     */
    String UM_RESOURCES_ENABLED_ALL = "UM_RESOURCES_ENABLED_ALL";
}
