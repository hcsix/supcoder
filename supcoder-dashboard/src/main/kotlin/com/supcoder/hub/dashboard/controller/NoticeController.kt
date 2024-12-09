package com.supcoder.hub.dashboard.controller

import com.supcoder.hub.dashboard.model.vo.NoticeIconItem
import com.supcoder.hub.dashboard.model.vo.NoticeIconItemType
import com.supcoder.hub.dashboard.model.vo.NoticeIconList
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * NoticeController
 *
 * @author lee
 * @date 2024/12/9
 */

@RestController
@RequestMapping("/api/notice")
class NoticeController {

    @GetMapping("/notices")
    fun getNotices(): ResponseEntity<NoticeIconList> {
        // 实现获取通知的逻辑
        val notices = NoticeIconList(
            data = listOf(
                NoticeIconItem(
                    id = "1",
                    extra = "extra info",
                    key = "key1",
                    read = false,
                    avatar = "http://example.com/avatar.jpg",
                    title = "New Feature",
                    status = "processing",
                    datetime = "2023-10-01",
                    description = "A new feature is available now.",
                    type = NoticeIconItemType.notification
                )
            ),
            total = 1,
            success = true
        )
        return ResponseEntity.ok(notices)
    }

}