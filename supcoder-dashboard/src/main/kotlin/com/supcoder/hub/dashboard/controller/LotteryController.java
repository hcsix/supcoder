package com.supcoder.hub.dashboard.controller;

/**
 * LotteryController
 *
 * @author lee
 * @date 2024/12/11
 */

import com.supcoder.hub.dashboard.model.LotteryCheckRequest;
import com.supcoder.hub.dashboard.model.LotteryCheckResult;
import com.supcoder.hub.dashboard.model.LotteryResult;
import com.supcoder.hub.dashboard.model.LotteryType;
import com.supcoder.hub.dashboard.model.LotteryWinningInfo;
import com.supcoder.hub.dashboard.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "valid_app_id".equals(appId) && "valid_app_secret".equals(appSecret);
    }

    @GetMapping("/{lotteryType}/{issueNumber}")
    public JsonResult<LotteryResult> getLotteryResultByIssueNumber(
            @PathVariable String lotteryType,
            @PathVariable String issueNumber,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return JsonResult.error("Invalid app_id or app_secret");
        }

        LotteryResult result = lotteryService.getLotteryResultByIssueNumber(lotteryType, issueNumber);
        if (result == null) {
            return JsonResult.error("Lottery result not found");
        }
        return JsonResult.success(result);
    }

    @GetMapping("/latest")
    public JsonResult<LotteryResult> getLatestLotteryResult(
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return JsonResult.error("Invalid app_id or app_secret");
        }

        LotteryResult result = lotteryService.getLatestLotteryResult();
        if (result == null) {
            return JsonResult.error("Latest lottery result not found");
        }
        return JsonResult.success(result);
    }

    @GetMapping("/history")
    public JsonResult<List<LotteryResult>> getRecentLotteryHistory(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return JsonResult.error("Invalid app_id or app_secret");
        }

        List<LotteryResult> results = lotteryService.getRecentLotteryHistory(limit);
        return JsonResult.success(results);
    }

    @GetMapping("/types")
    public JsonResult<List<LotteryType>> getSupportedLotteryTypes(
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return JsonResult.error("Invalid app_id or app_secret");
        }

        List<LotteryType> types = lotteryService.getSupportedLotteryTypes();
        return JsonResult.success(types);
    }

    @GetMapping("/result")
    public JsonResult<LotteryWinningInfo> getLotteryWinningInfo(
            @RequestParam String lotteryType,
            @RequestParam String issueNumber,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return JsonResult.error("Invalid app_id or app_secret");
        }

        LotteryWinningInfo winningInfo = lotteryService.getLotteryWinningInfo(lotteryType, issueNumber);
        if (winningInfo == null) {
            return JsonResult.error("Lottery winning info not found");
        }
        return JsonResult.success(winningInfo);
    }

    @PostMapping("/check")
    public JsonResult<LotteryCheckResult> checkLotteryWinning(
            @RequestBody LotteryCheckRequest request,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return JsonResult.error("Invalid app_id or app_secret");
        }

        LotteryCheckResult result = lotteryService.checkLotteryWinning(request.getLotteryType(), request.getIssueNumber(), request.getUserNumbers());
        return JsonResult.success(result);
    }
}

