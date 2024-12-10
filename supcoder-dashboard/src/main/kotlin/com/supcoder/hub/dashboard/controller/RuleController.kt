package com.supcoder.hub.dashboard.controller

import com.supcoder.hub.dashboard.model.vo.RuleList
import com.supcoder.hub.dashboard.model.vo.RuleListItem
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * RuleController
 *
 * @author lee
 * @date 2024/12/9
 */
@RestController
@RequestMapping("/api/rule")
open class RuleController {

    @GetMapping("/rule")
    fun getRules(@RequestParam current: Int, @RequestParam pageSize: Int): ResponseEntity<RuleList> {
        // 实现获取规则列表的逻辑
        val rules = RuleList(
            data = listOf(
                RuleListItem(
                    key = 1,
                    disabled = false,
                    href = "http://example.com/rule/1",
                    avatar = "http://example.com/avatar.jpg",
                    name = "Rule 1",
                    owner = "John Doe",
                    desc = "Description of rule 1",
                    callNo = 100,
                    status = 1,
                    updatedAt = "2023-10-01T12:00:00Z",
                    createdAt = "2023-09-01T12:00:00Z",
                    progress = 50
                )
            ),
            total = 1,
            success = true
        )
        return ResponseEntity.ok(rules)
    }

    @PostMapping("/rule")
    fun addRule(@RequestBody rule: RuleListItem): ResponseEntity<RuleListItem> {
        // 实现添加规则的逻辑
        return ResponseEntity.ok(rule)
    }

    @PutMapping("/rule")
    fun updateRule(@RequestBody rule: RuleListItem): ResponseEntity<RuleListItem> {
        // 实现更新规则的逻辑
        return ResponseEntity.ok(rule)
    }

    @DeleteMapping("/rule")
    fun deleteRule(): ResponseEntity<Any> {
        // 实现删除规则的逻辑
        return ResponseEntity.ok(mapOf("status" to "success"))
    }

}