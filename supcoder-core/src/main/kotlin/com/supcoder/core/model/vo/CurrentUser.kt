package com.supcoder.core.model.vo

/**
 * CurrentUser
 *
 * @author lee
 * @date 2024/12/9
 */
data class CurrentUser(
    val name: String,
    val avatar: String,
    val userid: String,
    val email: String,
    val signature: String,
    val title: String,
    val group: String,
    val tags: List<Tag>,
    val notifyCount: Int,
    val unreadCount: Int,
    val country: String,
    val access: String,
    val geographic: Geographic,
    val address: String,
    val phone: String
)

data class Tag(
    val key: String,
    val label: String
)

data class Geographic(
    val province: Province,
    val city: City
)

data class Province(
    val label: String,
    val key: String
)

data class City(
    val label: String,
    val key: String
)
