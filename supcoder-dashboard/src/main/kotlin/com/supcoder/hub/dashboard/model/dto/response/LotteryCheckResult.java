package com.supcoder.hub.dashboard.model.dto.response;

/**
 * LotteryCheckResult
 *
 * @author lee
 * @date 2024/12/11
 */
public class LotteryCheckResult {
    private boolean isWinner;
    private String message;

    // Getters and Setters
    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}