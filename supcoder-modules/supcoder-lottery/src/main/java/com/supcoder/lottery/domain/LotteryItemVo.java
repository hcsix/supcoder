package com.supcoder.lottery.domain;


/**
 * LotteryItem
 * <p>
 * {
 * "type": "sfc",
 * "name": "胜负彩(任九)",
 * "numbers": "3,3,3,3,1,0,3,3,3,0,0,1,0,3",
 * "money": "0",
 * "period": "24197期",
 * "date": "2024-12-12",
 * "draw_date": "不定期"
 * }
 *
 * @author lee
 * @date 2024/12/12
 */
public class LotteryItemVo {

    private String type;
    private String name;
    private String numbers;
    private String money;
    private String period;
    private String date;
    private String drawDate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }
}
