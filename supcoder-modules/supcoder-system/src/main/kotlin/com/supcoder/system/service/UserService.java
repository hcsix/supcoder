package com.supcoder.system.service;

import com.github.pagehelper.PageHelper;
import com.supcoder.hub.db.dao.UserMapper;
import com.supcoder.hub.db.domain.User;
import com.supcoder.hub.db.domain.UserExample;
import com.supcoder.hub.db.model.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User findById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public UserVo findUserVoById(Long userId) {
        User user = findById(userId);
        return new UserVo(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getAvatar(),
                user.getSignature(),
                user.getTitle(),
                user.getGroup(),
                user.getNotifyCount(),
                user.getUnreadCount()
        );
    }

    public User queryByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        return userMapper.selectOneByExample(userExample);
    }

    public void add(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setAddTime(now);
        user.setUpdateTime(now);
        userMapper.insertSelective(user);
    }

    public void updateById(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateByPrimaryKeySelective(user);
    }

    public Long count() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andDeletedEqualTo(false);
        return userMapper.countByExample(userExample);
    }

    public void deleteById(Long userId) {
        userMapper.logicalDeleteByPrimaryKey(userId);
    }

    public void deleteByUserName(String userName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(userName);
        userMapper.logicalDeleteByExample(userExample);
    }


    public boolean checkUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        return userMapper.countByExample(userExample) != 0;
    }

    public List<User> queryListByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        return userMapper.selectByExample(userExample);
    }

    public List<User> querySelective(
            String username,
            int page,
            int size,
            String sort,
            String order
    ) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            userExample.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, size);
        return userMapper.selectByExample(userExample);
    }



}
