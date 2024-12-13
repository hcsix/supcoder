package com.supcoder.lottery.domain;


import java.util.List;

/**
 * LotteryData
 *
 * @author lee
 * @date 2024/12/12
 */
public class LotteryDataVo {

    private List<LotteryItemVo> lotteryItems;

    private String secretKey;

    public List<LotteryItemVo> getLotteryItems() {
        return lotteryItems;
    }

    public void setLotteryItems(List<LotteryItemVo> lotteryItems) {
        this.lotteryItems = lotteryItems;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
