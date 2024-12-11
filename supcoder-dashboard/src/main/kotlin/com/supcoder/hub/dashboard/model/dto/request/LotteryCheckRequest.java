package com.supcoder.hub.dashboard.model.dto.request;

import java.util.List;

/**
 * LotteryCheckRequest
 *
 * @author lee
 * @date 2024/12/11
 */

public class LotteryCheckRequest {
    private String lotteryType;
    private String issueNumber;
    private List<String> userNumbers;

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

    public List<String> getUserNumbers() {
        return userNumbers;
    }

    public void setUserNumbers(List<String> userNumbers) {
        this.userNumbers = userNumbers;
    }
}








