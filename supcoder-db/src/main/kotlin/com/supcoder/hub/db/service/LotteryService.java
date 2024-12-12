package com.supcoder.hub.db.service;

import com.supcoder.hub.db.constants.Constants;
import com.supcoder.hub.db.dao.LotteryDaletouMapper;
import com.supcoder.hub.db.dao.LotteryShuangseqiuMapper;
import com.supcoder.hub.db.dao.LotteryTypeMapper;
import com.supcoder.hub.db.domain.*;
import com.supcoder.hub.db.model.LotteryDataVo;
import com.supcoder.hub.db.model.LotteryVo;
import com.supcoder.hub.db.service.lottery.DaletouStrategy;
import com.supcoder.hub.db.service.lottery.ILotteryStrategy;
import com.supcoder.hub.db.service.lottery.ShuangseqiuStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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


    public boolean updateLottery(LotteryDataVo lotteryData) {
        try {
            lotteryData.getLotteryItems().forEach(lotteryItem -> {
                // 根据lotteryItem.getType()选择对应的lotteryStrategy
                Optional.ofNullable(lotteryStrategies.get(lotteryItem.getType()))
                        .map(strategy -> strategy.updateLottery(lotteryItem))
                        .orElse(null);
                // 更新lotteryType
                LotteryTypeExample example = new LotteryTypeExample();
                example.or().andNameEqualTo(lotteryItem.getType());
                LotteryType lotteryType = lotteryTypeMapper.selectOneByExample(example);
                if (lotteryType == null){
                    lotteryType = new LotteryType();
                    lotteryType.setName(lotteryItem.getType());
                    lotteryType.setDescription(lotteryItem.getName());

                    lotteryType.setEnabled(Objects.equals(lotteryItem.getType(), Constants.LOTTERY_TYPE_SSQ)
                            || Objects.equals(lotteryItem.getType(), Constants.LOTTERY_TYPE_DLT));

                    lotteryType.setLatestDrawResult(lotteryItem.getNumbers());
                    lotteryType.setLatestIssueNumber(lotteryItem.getPeriod());
                    lotteryType.setUpdateTime(LocalDateTime.now());
                    lotteryTypeMapper.insert(lotteryType);
                } else {
                    lotteryType.setLatestDrawResult(lotteryItem.getNumbers());
                    lotteryType.setLatestIssueNumber(lotteryItem.getPeriod());
                    lotteryType.setUpdateTime(LocalDateTime.now());
                    lotteryTypeMapper.updateByExample(lotteryType, example);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
