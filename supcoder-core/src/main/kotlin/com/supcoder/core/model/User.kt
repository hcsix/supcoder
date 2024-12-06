package com.supcoder.core.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

/**
 * User
 *
 * @author lee
 * @date 2024/12/6
 */
@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    var username: String = "",
    var password: String = "",
    var email: String = ""
) {
    // 添加无参构造函数
    constructor() : this(0, "", "", "")
}
   