package com.supcoder.system.dashboard.model.vo

/**
 * RuleListItem
 *
 * @author lee
 * @date 2024/12/9
 */
data class RuleList(
    val data: List<RuleListItem>,
    val total: Int,
    val success: Boolean
)

data class RuleListItem(
    val key: Int,
    val disabled: Boolean,
    val href: String,
    val avatar: String,
    val name: String,
    val owner: String,
    val desc: String,
    val callNo: Int,
    val status: Int,
    val updatedAt: String,
    val createdAt: String,
    val progress: Int
)
