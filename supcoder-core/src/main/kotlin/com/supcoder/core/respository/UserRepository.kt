package com.supcoder.core.respository

import com.supcoder.core.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * UserRepository
 *
 * @author lee
 * @date 2024/12/6
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}