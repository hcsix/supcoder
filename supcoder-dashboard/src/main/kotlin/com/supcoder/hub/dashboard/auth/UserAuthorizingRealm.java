package com.supcoder.hub.dashboard.auth;


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
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 *
 */
public class UserAuthorizingRealm extends AuthorizingRealm {


    @Autowired
    @Lazy
    private RoleService roleService;
    @Autowired
    @Lazy
    private PermissionService permissionService;
    @Autowired
    @Lazy
    private UserService userService;

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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        if (StringUtils.isEmpty(username)) {
            throw new AccountException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new AccountException("密码不能为空");
        }
        List<User> userList = userService.queryListByUsername(username);
        Assert.state(userList.size() < 2, "同一个用户名存在两个账户");
        if (userList.isEmpty()) {
            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
        }
        User user = userList.get(0);
        if (!password.equals(user.getPassword())) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if (!encoder.matches(password, user.getPassword())) {
            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

}
