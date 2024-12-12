package com.supcoder.hub.db.service.lottery;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.supcoder.hub.db.constants.Constants;
import com.supcoder.hub.db.dao.LotteryDaletouMapper;
import com.supcoder.hub.db.domain.LotteryDaletou;
import com.supcoder.hub.db.domain.LotteryDaletouExample;
import com.supcoder.hub.db.model.LotteryItemVo;
import com.supcoder.hub.db.model.LotteryVo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * DaletouStrategy
 *
 * @author lee
 * @date 2024/12/11
 */
public class DaletouStrategy implements ILotteryStrategy {
    private final LotteryDaletouMapper mapper;

    public DaletouStrategy(LotteryDaletouMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public LotteryVo getLotteryVo(String issueNumber) {
        LotteryDaletouExample example = new LotteryDaletouExample();
        example.or().andIssueNumberEqualTo(issueNumber);
        return Optional.ofNullable(mapper.selectOneByExample(example))
                .map(lottery -> new LotteryVo(lottery.getIssueNumber(), Constants.LOTTERY_TYPE_DLT, lottery.getRedBalls() + "#" + lottery.getBlueBalls()))
                .orElse(null);
    }

    @Override
    public LotteryVo getLatestLotteryResult() {
        LotteryDaletouExample example = new LotteryDaletouExample();
        example.setOrderByClause("issue_number DESC");
        return Optional.ofNullable(mapper.selectOneByExample(example))
                .map(lottery -> new LotteryVo(lottery.getIssueNumber(), Constants.LOTTERY_TYPE_DLT, lottery.getRedBalls() + "#" + lottery.getBlueBalls()))
                .orElse(null);
    }

    @Override
    public List<LotteryVo> getRecentLotteryHistory(int limit) {
        try (Page<Object> page = PageHelper.startPage(0, limit)) {
            LotteryDaletouExample example = new LotteryDaletouExample();
            example.setOrderByClause("issue_number DESC");
            return mapper.selectByExample(example).stream()
                    .map(lottery -> new LotteryVo(lottery.getIssueNumber(), Constants.LOTTERY_TYPE_DLT, lottery.getRedBalls() + "#" + lottery.getBlueBalls()))
                    .toList();
        }
    }

    @Override
    public boolean updateLottery(LotteryItemVo lotteryVo) {
        try {
            LotteryDaletou lottery = new LotteryDaletou();
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