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

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
    //TODO 登录的鉴权和其他接口鉴权需区分
//        UsernamePasswordToken upToken = (UsernamePasswordToken) auth;
//        String username = upToken.getUsername();
//        String password = new String(upToken.getPassword());
//        if (StringUtils.isEmpty(username)) {
//            throw new AccountException("用户名不能为空");
//        }
//        if (StringUtils.isEmpty(password)) {
//            throw new AccountException("密码不能为空");
//        }
//        List<User> userList = userService.queryListByUsername(username);
//        Assert.state(userList.size() < 2, "同一个用户名存在两个账户");
//        if (userList.isEmpty()) {
//            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
//        }
//        User user = userList.get(0);
//        if (!password.equals(user.getPassword())) {
////        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
////        if (!encoder.matches(password, user.getPassword())) {
//            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
//        }
//
        String token = (String) auth.getCredentials();
        if (token == null || token.isEmpty()) {
            throw new AccountException("Token is missing");
        }

        if (!jwtUtil.validateToken(token)) {
            throw new IncorrectCredentialsException("Invalid token");
        }

        String username = jwtUtil.getUsernameFromToken(token);

        if (!userService.checkUsername(username)) {
            throw new UnknownAccountException("No account found for user [" + username + "]");
        }
        if (username == null) {
            throw new UnknownAccountException("Username not found in token");
        }

        return new SimpleAuthenticationInfo(username, token, getName());
    }
}

