package com.supcoder.lottery.service.lottery;


import com.supcoder.lottery.domain.LotteryItemVo;
import com.supcoder.lottery.domain.LotteryVo;

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

    boolean updateLottery(LotteryItemVo lotteryVo);
}

