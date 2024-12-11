package com.supcoder.hub.dashboard.controller;

import com.supcoder.hub.core.util.ResultUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * FilterErrorController
 * 重定向错误接口
 */
@RestController
public class FilterErrorController {

    @RequestMapping("/filterError/{code}/{message}")
    public ResponseEntity error(@PathVariable("code") Integer code, @PathVariable("message") String message) {
        return ResponseEntity.ok(ResultUtil.error(code, message));
    }
}
