package com.supcoder.hub.dashboard.controller;

/**
 * LotteryController
 *
 * @author lee
 * @date 2024/12/11
 */

import com.supcoder.hub.core.util.JsonResult;
import com.supcoder.hub.core.util.ResultUtil;
import com.supcoder.hub.dashboard.model.dto.request.LotteryCheckRequest;
import com.supcoder.hub.dashboard.model.dto.response.LotteryCheckResult;
import com.supcoder.hub.dashboard.model.dto.response.LotteryResult;
import com.supcoder.hub.dashboard.model.dto.response.LotteryWinningInfo;
import com.supcoder.hub.db.domain.LotteryType;
import com.supcoder.hub.db.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lottery")
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;
    private boolean isValidApp(String appId, String appSecret) {
        // 这里可以添加对 app_id 和 app_secret 的验证逻辑
        // 示例：假设有一个简单的验证逻辑
//        return "valid_app_id".equals(appId) && "valid_app_secret".equals(appSecret);
        return true;
    }


    @GetMapping("/types")
    public ResponseEntity<JsonResult<List<LotteryType>>> getSupportedLotteryTypes(
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return ResponseEntity.ok(ResultUtil.error(502,"Invalid app_id or app_secret"));
        }
        List<LotteryType> types = lotteryService.getSupportedLotteryTypes();
        return ResponseEntity.ok(ResultUtil.success(types));
    }




    @GetMapping("/{lotteryType}/{issueNumber}")
    public ResponseEntity<JsonResult<?>>  getLotteryResultByIssueNumber(
            @PathVariable String lotteryType,
            @PathVariable String issueNumber,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return ResponseEntity.ok(ResultUtil.error(502,"Invalid app_id or app_secret"));
        }

        LotteryResult result = lotteryService.getLotteryResultByIssueNumber(lotteryType, issueNumber);
        if (result == null) {
            return ResponseEntity.ok(ResultUtil.error(502,"Lottery result not found"));
        }
        return ResponseEntity.ok(ResultUtil.success(result));
    }

    @GetMapping("/{lotteryType}/latest")
    public ResponseEntity<JsonResult<LotteryResult>> getLatestLotteryResult(
            @PathVariable String lotteryType,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return ResponseEntity.ok(ResultUtil.error(502,"Invalid app_id or app_secret"));
        }

        LotteryResult result = lotteryService.getLatestLotteryResult();
        if (result == null) {
            return  ResponseEntity.ok(ResultUtil.error(502,"Latest lottery result not found");
        }
        return ResponseEntity.ok(ResultUtil.success(result));
    }

    @GetMapping("/{lotteryType}/history")
    public ResponseEntity<JsonResult<List<LotteryResult>>> getRecentLotteryHistory(
            @PathVariable String lotteryType,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return ResponseEntity.ok(ResultUtil.error(502,"Invalid app_id or app_secret"));
        }

        List<LotteryResult> results = lotteryService.getRecentLotteryHistory(limit);
        return ResponseEntity.ok(ResultUtil.success(results));
    }


    @GetMapping("/{lotteryType}/result")
    public ResponseEntity<JsonResult<LotteryWinningInfo>> getLotteryWinningInfo(
            @PathVariable String lotteryType,
            @RequestParam String issueNumber,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return ResponseEntity.ok(ResultUtil.error(502,"Invalid app_id or app_secret"));
        }
        LotteryWinningInfo winningInfo = lotteryService.getLotteryWinningInfo(lotteryType, issueNumber);
        if (winningInfo == null) {
            return  ResponseEntity.ok(ResultUtil.error(502,"Lottery winning info not found"));
        }
        return ResponseEntity.ok(ResultUtil.success(winningInfo));
    }

    @PostMapping("/check")
    public ResponseEntity<JsonResult<LotteryCheckResult>> checkLotteryWinning(
            @RequestBody LotteryCheckRequest request,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return ResponseEntity.ok(ResultUtil.error(502,"Invalid app_id or app_secret"));
        }

        LotteryCheckResult result = lotteryService.checkLotteryWinning(request.getLotteryType(), request.getIssueNumber(), request.getUserNumbers());
        return ResponseEntity.ok(ResultUtil.success(result));
    }
}

