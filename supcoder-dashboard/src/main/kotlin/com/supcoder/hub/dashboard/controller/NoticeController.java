package com.supcoder.hub.dashboard.controller;

import com.supcoder.hub.core.util.JsonResult;
import com.supcoder.hub.core.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.supcoder.hub.dashboard.model.vo.NoticeIconItem;
import com.supcoder.hub.dashboard.model.vo.NoticeIconItemType;
import com.supcoder.hub.dashboard.model.vo.NoticeIconList;

@RestController
@RequestMapping("/api")
public class NoticeController {

    @GetMapping("/notices")
    @RequiresAuthentication
    public ResponseEntity<JsonResult<NoticeIconList>> notices() {
        // 实现获取通知列表的逻辑
        // 实现获取通知的逻辑
        List<NoticeIconItem> list = new ArrayList<>();
        list.add(new NoticeIconItem(
                "1",
                "extra info",
                "key1",
                false,
                "http://example.com/avatar.jpg",
                "New Feature",
                "processing",
                "2023-10-01",
                "A new feature is available now.",
                NoticeIconItemType.notification
        ));

        NoticeIconList notices = new NoticeIconList(
                list,
                1,
                true
        );
        return ResponseEntity.ok(ResultUtil.success(notices));
    }
}
