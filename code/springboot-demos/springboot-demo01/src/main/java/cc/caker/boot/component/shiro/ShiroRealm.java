package cc.caker.boot.component.shiro;

import cc.caker.boot.repo.mapper.db1.AdminMapper;
import cc.caker.boot.repo.model.db1.Admin;
import com.google.common.base.Strings;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static cc.caker.boot.util.EncryptUtils.ENCRYPT_TYPE;
import static cc.caker.boot.util.EncryptUtils.HASH_ITERATIONS;

/**
 * Shiro 授权authorization 认证authentication
 *
 * @author cakeralter
 * @date 2020/8/8
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private AdminMapper adminMapper;

    {
        // 设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName(ENCRYPT_TYPE);
        hashMatcher.setStoredCredentialsHexEncoded(true);
        // 加密的次数
        hashMatcher.setHashIterations(HASH_ITERATIONS);
        setCredentialsMatcher(hashMatcher);
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (Strings.isNullOrEmpty(token.getUsername())) {
            throw new AccountException("用户名为空!");
        }
        Admin origin = adminMapper.findByUsername(token.getUsername());
        if (Objects.isNull(origin)) {
            throw new UnknownAccountException("账号不存在!");
        }
        return new SimpleAuthenticationInfo(origin, origin.getPassword(),
                ByteSource.Util.bytes(origin.getSecret()), origin.getUsername());
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (Objects.isNull(principals)) {
            throw new AuthorizationException("用户认证出错");
        }
        Admin principal = (Admin) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(principal.getRoles());
        info.setStringPermissions(principal.getPerms());
        return info;
    }
}
