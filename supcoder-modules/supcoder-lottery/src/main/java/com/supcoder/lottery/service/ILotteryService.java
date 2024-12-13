package com.supcoder.lottery.service;

import com.supcoder.lottery.domain.LotteryItemVo;
import com.supcoder.lottery.domain.LotteryType;
import com.supcoder.lottery.domain.LotteryVo;

import java.util.List;

/**
 * ILotteryService
 *
 * @author lee
 * @date 2024/12/13
 */
public interface ILotteryService {

    public List<LotteryType> getSupportedLotteryTypes();


    public LotteryVo getLotteryVoByIssueNumber(String lotteryType, String issueNumber);


    public LotteryVo getLotteryWinningInfo(String lotteryType, String issueNumber);


    public List<LotteryVo> getRecentLotteryHistory(String lotteryType, int limit);


    public LotteryVo getLatestLotteryResult(String lotteryType);


    public boolean updateLottery(List<LotteryItemVo> lotteryData);
}
