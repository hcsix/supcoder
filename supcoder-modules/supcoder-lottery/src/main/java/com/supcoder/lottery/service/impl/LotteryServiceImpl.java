package com.supcoder.lottery.service.impl;


import com.supcoder.lottery.constants.Constants;
import com.supcoder.lottery.domain.LotteryItemVo;
import com.supcoder.lottery.domain.LotteryType;
import com.supcoder.lottery.domain.LotteryTypeExample;
import com.supcoder.lottery.domain.LotteryVo;
import com.supcoder.lottery.mapper.LotteryDaletouMapper;
import com.supcoder.lottery.mapper.LotteryShuangseqiuMapper;
import com.supcoder.lottery.mapper.LotteryTypeMapper;
import com.supcoder.lottery.service.ILotteryService;
import com.supcoder.lottery.service.impl.lottery.DltStrategy;
import com.supcoder.lottery.service.impl.lottery.ILotteryStrategy;
import com.supcoder.lottery.service.impl.lottery.SsqStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class LotteryServiceImpl implements ILotteryService {

    @Autowired
    private LotteryTypeMapper lotteryTypeMapper;

    @Autowired
    private LotteryShuangseqiuMapper lotteryShuangseqiuMapper;

    @Autowired
    private LotteryDaletouMapper lotteryDaletouMapper;

    private final Map<String, ILotteryStrategy> lotteryStrategies = new HashMap<>();

    public LotteryServiceImpl() {
        lotteryStrategies.put(Constants.LOTTERY_TYPE_SSQ, new SsqStrategy(lotteryShuangseqiuMapper));
        lotteryStrategies.put(Constants.LOTTERY_TYPE_DLT, new DltStrategy(lotteryDaletouMapper));
    }


    @Override
    public List<LotteryType> getSupportedLotteryTypes() {
        LotteryTypeExample example = new LotteryTypeExample();
        example.createCriteria().andEnabledEqualTo(true);
        return lotteryTypeMapper.selectByExample(example);
    }

    @Override
    public LotteryVo getLotteryVoByIssueNumber(String lotteryType, String issueNumber) {
        return getLotteryVo(lotteryType, issueNumber, false);
    }

    @Override
    public LotteryVo getLotteryWinningInfo(String lotteryType, String issueNumber) {
        return getLotteryVo(lotteryType, issueNumber, true);
    }

    private LotteryVo getLotteryVo(String lotteryType, String issueNumber, boolean winningInfo) {
        return Optional.ofNullable(lotteryStrategies.get(lotteryType))
                .map(strategy -> strategy.getLotteryVo(issueNumber))
                .orElse(null);
    }

    @Override
    public LotteryVo getLatestLotteryResult(String lotteryType) {
        return Optional.ofNullable(lotteryStrategies.get(lotteryType))
                .map(ILotteryStrategy::getLatestLotteryResult)
                .orElse(null);
    }
    @Override
    public List<LotteryVo> getRecentLotteryHistory(String lotteryType, int limit) {
        return Optional.ofNullable(lotteryStrategies.get(lotteryType))
                .map(strategy -> strategy.getRecentLotteryHistory(limit))
                .orElse(null);
    }


    @Override
    public boolean updateLottery( List<LotteryItemVo>  lotteryData) {
        try {
            lotteryData.forEach(lotteryItem -> {
                // 根据lotteryItem.getType()选择对应的lotteryStrategy
                Optional.ofNullable(lotteryStrategies.get(lotteryItem.getType()))
                        .map(strategy -> strategy.updateLottery(lotteryItem))
                        .orElse(null);
                // 更新lotteryType
                LotteryTypeExample example = new LotteryTypeExample();
                example.createCriteria().andNameEqualTo(lotteryItem.getType());
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
                    lotteryType.setDeleted(false);
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
