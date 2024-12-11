package com.supcoder.hub.db.service;

import com.supcoder.hub.db.constants.Constants;
import com.supcoder.hub.db.dao.LotteryDaletouMapper;
import com.supcoder.hub.db.dao.LotteryShuangseqiuMapper;
import com.supcoder.hub.db.dao.LotteryTypeMapper;
import com.supcoder.hub.db.domain.*;
import com.supcoder.hub.db.model.LotteryVo;
import com.supcoder.hub.db.service.lottery.DaletouStrategy;
import com.supcoder.hub.db.service.lottery.ILotteryStrategy;
import com.supcoder.hub.db.service.lottery.ShuangseqiuStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LotteryService {

    @Autowired
    private LotteryTypeMapper lotteryTypeMapper;

    @Autowired
    private LotteryShuangseqiuMapper lotteryShuangseqiuMapper;

    @Autowired
    private LotteryDaletouMapper lotteryDaletouMapper;

    private final Map<String, ILotteryStrategy> lotteryStrategies = new HashMap<>();

    public LotteryService() {
        lotteryStrategies.put(Constants.LOTTERY_TYPE_SSQ, new ShuangseqiuStrategy(lotteryShuangseqiuMapper));
        lotteryStrategies.put(Constants.LOTTERY_TYPE_DLT, new DaletouStrategy(lotteryDaletouMapper));
    }

    public List<LotteryType> getSupportedLotteryTypes() {
        LotteryTypeExample example = new LotteryTypeExample();
        example.or().andEnabledEqualTo(true);
        return lotteryTypeMapper.selectByExample(example);
    }

    public LotteryVo getLotteryVoByIssueNumber(String lotteryType, String issueNumber) {
        return getLotteryVo(lotteryType, issueNumber, false);
    }

    public LotteryVo getLotteryWinningInfo(String lotteryType, String issueNumber) {
        return getLotteryVo(lotteryType, issueNumber, true);
    }

    private LotteryVo getLotteryVo(String lotteryType, String issueNumber, boolean winningInfo) {
        return Optional.ofNullable(lotteryStrategies.get(lotteryType))
                .map(strategy -> strategy.getLotteryVo(issueNumber))
                .orElse(null);
    }

    public LotteryVo getLatestLotteryResult(String lotteryType) {
        return Optional.ofNullable(lotteryStrategies.get(lotteryType))
                .map(ILotteryStrategy::getLatestLotteryResult)
                .orElse(null);
    }

    public List<LotteryVo> getRecentLotteryHistory(String lotteryType, int limit) {
        return Optional.ofNullable(lotteryStrategies.get(lotteryType))
                .map(strategy -> strategy.getRecentLotteryHistory(limit))
                .orElse(null);
    }

}
