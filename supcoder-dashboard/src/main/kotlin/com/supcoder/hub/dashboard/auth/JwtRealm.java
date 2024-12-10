package com.supcoder.hub.dashboard.auth;

/**
 * JwtRealm
 *
 * @author lee
 * @date 2024/12/10
 */

import com.supcoder.hub.db.domain.User;
import com.supcoder.hub.db.service.PermissionService;
import com.supcoder.hub.db.service.RoleService;
import com.supcoder.hub.db.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

public class JwtRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private RoleService roleService;
    @Autowired
    @Lazy
    private PermissionService permissionService;
    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private JwtUtil jwtUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        User user = (User) getAvailablePrincipal(principals);
        Integer[] roleIds = user.getRoleIds();
        Set<String> roles = roleService.queryByIds(roleIds);
        Set<String> permissions = permissionService.queryByRoleIds(roleIds);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 重写doGetAuthenticationInfo方法以实现自定义的认证逻辑
     * 此方法主要用于验证用户的身份信息，包括用户名和密码，或令牌信息
     *
     * @param auth 认证令牌，包含用户提交的认证信息
     * @return 返回AuthenticationInfo对象，其中包含主体的认证信息
     * @throws AuthenticationException 如果认证失败，则抛出此异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        // 获取令牌信息，用于JWT认证
        String token = (String) auth.getCredentials();
        if (token == null || token.isEmpty()) {
            throw new AccountException("Token is missing");
        }
        // 验证令牌有效性
        if (!jwtUtil.validateToken(token)) {
            throw new IncorrectCredentialsException("Invalid token");
        }
        // 从令牌中提取用户名
        String username = jwtUtil.extractUsername(token);

        // 检查用户名是否存在
        if (!userService.checkUsername(username)) {
            throw new UnknownAccountException("No account found for user [" + username + "]");
        }
        if (username == null) {
            throw new UnknownAccountException("Username not found in token");
        }

        // 返回认证信息，包括用户名、令牌和当前认证器的名称
        return new SimpleAuthenticationInfo(username, token, getName());
    }

}

