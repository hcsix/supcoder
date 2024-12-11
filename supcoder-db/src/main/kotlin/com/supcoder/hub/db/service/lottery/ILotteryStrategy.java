package com.supcoder.hub.db.service.lottery;

import com.supcoder.hub.db.model.LotteryVo;

import java.util.List;

/**
 * LotteryStrategy
 *
 * @author lee
 * @date 2024/12/11
 */
public interface ILotteryStrategy {
    LotteryVo getLotteryVo(String issueNumber);
    LotteryVo getLatestLotteryResult();
    List<LotteryVo> getRecentLotteryHistory(int limit);
}

