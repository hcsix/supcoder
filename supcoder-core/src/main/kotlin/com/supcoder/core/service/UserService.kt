package com.supcoder.core.service

import com.supcoder.core.model.User
import com.supcoder.core.respository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * UserService
 *
 * @author lee
 * @date 2024/12/6
 */
@Service
class UserService {


    @Autowired
    private lateinit var userRepository: UserRepository

    fun findAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun findUserById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun deleteUserById(id: Long) {
        userRepository.deleteById(id)
    }

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }
}