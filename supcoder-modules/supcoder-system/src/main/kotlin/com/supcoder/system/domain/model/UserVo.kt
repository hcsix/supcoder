package com.supcoder.hub.db.model

/**
 * UserVo
 *
 * @author lee
 * @date 2024/12/9
 */
 data class UserVo(
    val id: Long = 0,
    val username: String,
    val email: String,
    var password: String,
    val name: String,
    val avatar: String? = null,
    val signature: String? = null,
    val title: String? = null,
    val group: String? = null,
    val notifyCount: Int? = null,
    val unreadCount: Int? = null,
)

