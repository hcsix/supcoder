package com.supcoder.system.controller;

import com.supcoder.hub.dashboard.model.vo.RuleList;
import com.supcoder.hub.dashboard.model.vo.RuleListItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RuleController
 *
 * @author lee
 * @date 2024/12/9
 */
@RestController
@RequestMapping("/api/rule")
public class RuleController {

    @GetMapping("/rule")
    public ResponseEntity<RuleList> getRules(@RequestParam int current, @RequestParam int pageSize) {
        // 实现获取规则列表的逻辑
        List<RuleListItem> rulesData = Collections.singletonList(
                new RuleListItem(
                        1,
                        false,
                        "http://example.com/rule/1",
                        "http://example.com/avatar.jpg",
                        "Rule 1",
                        "John Doe",
                        "Description of rule 1",
                        100,
                        1,
                        "2023-10-01T12:00:00Z",
                        "2023-09-01T12:00:00Z",
                        50
                )
        );

        RuleList rules = new RuleList(rulesData, 1, true);
        return ResponseEntity.ok(rules);
    }

    @PostMapping("/rule")
    public ResponseEntity<RuleListItem> addRule(@RequestBody RuleListItem rule) {
        // 实现添加规则的逻辑
        return ResponseEntity.ok(rule);
    }

    @PutMapping("/rule")
    public ResponseEntity<RuleListItem> updateRule(@RequestBody RuleListItem rule) {
        // 实现更新规则的逻辑
        return ResponseEntity.ok(rule);
    }

    @DeleteMapping("/rule")
    public ResponseEntity<Map<String, String>> deleteRule() {
        // 实现删除规则的逻辑
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}
