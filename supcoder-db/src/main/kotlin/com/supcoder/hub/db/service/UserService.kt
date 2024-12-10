package com.supcoder.hub.db.service

import com.github.pagehelper.PageHelper
import com.supcoder.hub.db.dao.UserMapper
import com.supcoder.hub.db.domain.User
import com.supcoder.hub.db.domain.UserExample
import com.supcoder.hub.db.model.UserVo
import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.time.LocalDateTime

@Service
open class UserService {

    @Resource
    private lateinit var userMapper: UserMapper


    open fun findById(userId: Long): User {
        return userMapper.selectByPrimaryKey(userId);
    }

    open fun findUserVoById(userId: Long): UserVo {
        val user = findById(userId)
        return UserVo(
            id = user.id,
            username = user.username,
            email = user.email,
            password = user.password,
            name = user.name,
            avatar = user.avatar,
            signature = user.signature,
            title = user.title,
            group = user.group,
            notifyCount = user.notifyCount,
            unreadCount = user.unreadCount,
        )
    }

    open fun queryByUsername(username: String): User? {
        val userExample = UserExample()
        userExample.or().andUsernameEqualTo(username)
        return userMapper.selectOneByExample(userExample)
    }

    open fun add(user: User) {
        val now = LocalDateTime.now()
        user.addTime = now
        user.updateTime = now
        userMapper.insertSelective(user)
    }

    open fun updateById(user: User) {
        user.updateTime = LocalDateTime.now()
        userMapper.updateByPrimaryKeySelective(user)
    }

    open fun count(): Long {
        val userExample = UserExample()
        userExample.or().andDeletedEqualTo(false)
        return userMapper.countByExample(userExample)
    }

    open fun deleteById(userId: Long) {
        userMapper.logicalDeleteByPrimaryKey(userId)
    }

    open fun checkUsername(username: String): Boolean {
        val userExample = UserExample()
        userExample.or().andUsernameEqualTo(username)
        return userMapper.countByExample(userExample).toInt() != 0
    }

    open fun queryListByUsername(username: String): List<User> {
        val userExample = UserExample()
        userExample.or().andUsernameEqualTo(username)
        return userMapper.selectByExample(userExample)
    }

    open fun querySelective(
        username: String?,
        page: Int,
        size: Int,
        sort: String,
        order: String
    ): List<User> {
        val userExample = UserExample()
        val criteria = userExample.createCriteria()
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%$username%")
        }
        criteria.andDeletedEqualTo(false)
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            userExample.setOrderByClause("$sort $order")
        }
        PageHelper.startPage<User>(page, size)
        return userMapper.selectByExample(userExample)
    }


}

