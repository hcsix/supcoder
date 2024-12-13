package com.supcoder.lottery.service.lottery;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supcoder.lottery.constants.Constants;
import com.supcoder.lottery.domain.LotteryItemVo;
import com.supcoder.lottery.domain.LotteryShuangseqiu;
import com.supcoder.lottery.domain.LotteryShuangseqiuExample;
import com.supcoder.lottery.domain.LotteryVo;
import com.supcoder.lottery.mapper.LotteryShuangseqiuMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * ShuangseqiuStrategy
 *
 * @author lee
 * @date 2024/12/11
 */

public class SsqStrategy implements ILotteryStrategy {
    private final LotteryShuangseqiuMapper mapper;

    public SsqStrategy(LotteryShuangseqiuMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public LotteryVo getLotteryVo(String issueNumber) {
        LotteryShuangseqiuExample example = new LotteryShuangseqiuExample();
        example.or().andIssueNumberEqualTo(issueNumber);
        return Optional.ofNullable(mapper.selectOneByExample(example))
                .map(lottery -> new LotteryVo(lottery.getIssueNumber(), Constants.LOTTERY_TYPE_SSQ, lottery.getRedBalls() + "#" + lottery.getBlueBalls()))
                .orElse(null);
    }

    @Override
    public LotteryVo getLatestLotteryResult() {
        LotteryShuangseqiuExample example = new LotteryShuangseqiuExample();
        example.setOrderByClause("issue_number DESC");
        return Optional.ofNullable(mapper.selectOneByExample(example))
                .map(lottery -> new LotteryVo(lottery.getIssueNumber(), Constants.LOTTERY_TYPE_SSQ, lottery.getRedBalls() + "#" + lottery.getBlueBalls()))
                .orElse(null);
    }

    @Override
    public List<LotteryVo> getRecentLotteryHistory(int limit) {
        try (Page<Object> page = PageHelper.startPage(0, limit)) {
            LotteryShuangseqiuExample example = new LotteryShuangseqiuExample();
            example.setOrderByClause("issue_number DESC");
            return mapper.selectByExample(example).stream()
                    .map(lottery -> new LotteryVo(lottery.getIssueNumber(), Constants.LOTTERY_TYPE_SSQ, lottery.getRedBalls() + "#" + lottery.getBlueBalls()))
                    .toList();
        }
    }

    @Override
    public boolean updateLottery(LotteryItemVo lotteryVo) {
        try {
            LotteryShuangseqiu lottery = new LotteryShuangseqiu();
            lottery.setIssueNumber(lotteryVo.getPeriod());
            String[] balls = lotteryVo.getNumbers().split("\\|");
            if (balls.length != 2) {
                throw new IllegalArgumentException("Invalid numbers format");
            }
            lottery.setRedBalls(balls[0]);
            lottery.setBlueBalls(balls[1]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime drawDate = LocalDateTime.parse(lotteryVo.getDate(), formatter);
            lottery.setDrawDate(drawDate);
            lottery.setAddTime(LocalDateTime.now());
            lottery.setUpdateTime(LocalDateTime.now());
            mapper.insert(lottery);
            return true;
        } catch (Exception e) {
            // 记录日志或进行其他异常处理
            e.printStackTrace();
            return false;
        }
    }
}
