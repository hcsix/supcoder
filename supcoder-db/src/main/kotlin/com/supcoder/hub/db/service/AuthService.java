package com.supcoder.hub.db.service;

import com.supcoder.hub.db.domain.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AuthService
 *
 * @author lee
 * @date 2024/12/10
 */
@Service
public class AuthService {
    @Autowired
    private UserService userService;

    /**
     * 验证用户名和密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 如果验证成功，返回用户对象；否则返回 null
     */
    public User authenticate(String username, String password) {
        User user = userService.queryByUsername(username);
        if (user == null) {
            return null;
        }
//        if (BCrypt.checkpw(password, user.getPassword())) {
//            return user;
//        }
        if (password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }


    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = userService.queryByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
//        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
        if (!oldPassword.equals(user.getPassword())) {
            throw new RuntimeException("Old password does not match");
        }
//        user.setPassword(hashPassword(newPassword));
        user.setPassword(newPassword);
        userService.updateById(user);
    }

    /**
     * 使用 bcrypt 加密密码
     *
     * @param password 明文密码
     * @return 加密后的密码
     */
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
