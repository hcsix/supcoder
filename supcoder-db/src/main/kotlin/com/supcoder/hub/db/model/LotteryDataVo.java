package com.supcoder.hub.db.model;

import java.util.List;

/**
 * LotteryData
 *
 * @author lee
 * @date 2024/12/12
 */
public class LotteryDataVo {

    private List<LotteryItemVo> lotteryItems;

    public List<LotteryItemVo> getLotteryItems() {
        return lotteryItems;
    }

    public void setLotteryItems(List<LotteryItemVo> lotteryItems) {
        this.lotteryItems = lotteryItems;
    }
}
