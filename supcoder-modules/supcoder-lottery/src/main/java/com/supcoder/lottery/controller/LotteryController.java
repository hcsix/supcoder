package com.supcoder.lottery.controller;


import com.supcoder.common.core.web.controller.BaseController;
import com.supcoder.common.core.web.domain.AjaxResult;
import com.supcoder.lottery.domain.LotteryDataVo;
import com.supcoder.lottery.domain.LotteryType;
import com.supcoder.lottery.domain.LotteryVo;
import com.supcoder.lottery.service.ILotteryService;
import com.supcoder.lottery.service.impl.LotteryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LotteryController
 *
 * @author lee
 * @date 2024/12/11
 */

@RestController
@RequestMapping("/api/lottery")
public class LotteryController extends BaseController {

    @Autowired
    private ILotteryService lotteryService;

    private boolean isValidApp(String appId, String appSecret) {
        // 这里可以添加对 app_id 和 app_secret 的验证逻辑
        // 示例：假设有一个简单的验证逻辑
//        return "valid_app_id".equals(appId) && "valid_app_secret".equals(appSecret);
        return true;
    }


    @GetMapping("/types")
    public AjaxResult getSupportedLotteryTypes(
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return error("Invalid app_id or app_secret");
        }
        List<LotteryType> types = lotteryService.getSupportedLotteryTypes();
        return success(types);
    }


    @GetMapping("/{lotteryType}/{issueNumber}")
    public AjaxResult getLotteryResultByIssueNumber(
            @PathVariable String lotteryType,
            @PathVariable String issueNumber,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return error("Invalid app_id or app_secret");
        }
        LotteryVo result = lotteryService.getLotteryVoByIssueNumber(lotteryType, issueNumber);
        if (result == null) {
            return error("Lottery result not found");
        }
        return success(result);
    }

    @GetMapping("/{lotteryType}/latest")
    public AjaxResult getLatestLotteryResult(
            @PathVariable String lotteryType,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return error("Invalid app_id or app_secret");
        }

        LotteryVo result = lotteryService.getLatestLotteryResult(lotteryType);
        if (result == null) {
            return error("Latest lottery result not found");
        }
        return success(result);
    }

    @GetMapping("/{lotteryType}/history")
    public AjaxResult getRecentLotteryHistory(
            @PathVariable String lotteryType,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam String app_id,
            @RequestParam String app_secret) {
        if (!isValidApp(app_id, app_secret)) {
            return error("Invalid app_id or app_secret");
        }
        List<LotteryVo> results = lotteryService.getRecentLotteryHistory(lotteryType, limit);
        return success(results);
    }


    @PostMapping("/manager/updateLottery")
    public AjaxResult updateLottery(
            @RequestBody LotteryDataVo lotteryData
    ) {
//        if (!isValidApp(app_id, app_secret)) {
//            return error( "Invalid app_id or app_secret");
//        }
        // 调用服务方法更新彩票结果
        boolean updated = lotteryService.updateLottery(lotteryData.getLotteryItems());
        if (!updated) {
            return error("Failed to update lottery result");
        }
        return success("Lottery result updated successfully");
    }


}

