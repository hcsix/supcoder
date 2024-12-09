package com.supcoder.core.model.vo

/**
 * Notice
 *
 * @author lee
 * @date 2024/12/9
 */
data class NoticeIconList(
    val data: List<NoticeIconItem>,
    val total: Int,
    val success: Boolean
)

data class NoticeIconItem(
    val id: String,
    val extra: String,
    val key: String,
    val read: Boolean,
    val avatar: String,
    val title: String,
    val status: String,
    val datetime: String,
    val description: String,
    val type: NoticeIconItemType
)

enum class NoticeIconItemType {
    notification,
    message,
    event
}