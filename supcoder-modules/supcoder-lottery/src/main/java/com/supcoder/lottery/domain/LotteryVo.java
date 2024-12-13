package com.supcoder.lottery.domain;


/**
 * LotteryResult
 *
 * @author lee
 * @date 2024/12/11
 */
public class LotteryVo {
    private String lotteryType;
    private String issueNumber;
    private String result;

    public LotteryVo(String lotteryType, String issueNumber, String result) {
        this.lotteryType = lotteryType;
        this.issueNumber = issueNumber;
        this.result = result;
    }

    // Getters and Setters
    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}