package com.supcoder.hub.dashboard.model.dto.response;

import java.util.List;

/**
 * LotteryResult
 *
 * @author lee
 * @date 2024/12/11
 */
public class LotteryResult {
    private String lotteryType;
    private String issueNumber;
    private List<String> winningNumbers;

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

    public List<String> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(List<String> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }
}