package com.supcoder.hub.dashboard.model.dto.response;

/**
 * LotteryWinningInfo
 *
 * @author lee
 * @date 2024/12/11
 */
public class LotteryWinningInfo {
    private String lotteryType;
    private String issueNumber;
    private String winningAmount;

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

    public String getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(String winningAmount) {
        this.winningAmount = winningAmount;
    }
}

