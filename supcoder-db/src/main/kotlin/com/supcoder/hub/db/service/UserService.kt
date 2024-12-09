package com.supcoder.hub.db.service

import com.alibaba.druid.util.StringUtils
import com.github.pagehelper.PageHelper
import com.supcoder.hub.db.dao.UserMapper
import com.supcoder.hub.db.domain.User
import com.supcoder.hub.db.domain.UserExample
import com.supcoder.hub.db.model.UserVo
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.annotation.Resource

@Service
class UserService {

    @Resource
    private lateinit var userMapper: UserMapper


    fun findById(userId: Long): User {
        return userMapper.selectByPrimaryKey(userId);
    }

    fun findUserVoById(userId: Long): UserVo {
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

    fun queryByUsername(username: String): User? {
        val userExample = UserExample()
        userExample.or().andUsernameEqualTo(username)
        return userMapper.selectOneByExample(userExample)
    }

    fun add(user: User) {
        val now = LocalDateTime.now()
        user.addTime = now
        user.updateTime = now
        userMapper.insertSelective(user)
    }

    fun updateById(user: User) {
        user.updateTime = LocalDateTime.now()
        userMapper.updateByPrimaryKeySelective(user)
    }

    fun count(): Long {
        val userExample = UserExample()
        userExample.or().andDeletedEqualTo(false)
        return userMapper.countByExample(userExample)
    }

    fun deleteById(userId: Long) {
        userMapper.logicalDeleteByPrimaryKey(userId)
    }

    fun checkUsername(username: String): Boolean {
        val userExample = UserExample()
        userExample.or().andUsernameEqualTo(username)
        return userMapper.countByExample(userExample).toInt() != 0
    }

    fun queryListByUsername(username: String): List<User> {
        val userExample = UserExample()
        userExample.or().andUsernameEqualTo(username)
        return userMapper.selectByExample(userExample)
    }


    fun querySelective(
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

