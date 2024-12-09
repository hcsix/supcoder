package com.supcoder.hub.dashboard.model.domain

data class User(
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
    val country: String? = null,
    val access: String? = null,
    val address: String? = null,
    val phone: String? = null
)
