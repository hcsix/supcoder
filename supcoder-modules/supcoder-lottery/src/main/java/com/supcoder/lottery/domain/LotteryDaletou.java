package com.supcoder.lottery.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class LotteryDaletou {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.issue_number
     *
     * @mbg.generated
     */
    private String issueNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.red_balls
     *
     * @mbg.generated
     */
    private String redBalls;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.blue_balls
     *
     * @mbg.generated
     */
    private String blueBalls;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.draw_date
     *
     * @mbg.generated
     */
    private LocalDateTime drawDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.jackpot_amount
     *
     * @mbg.generated
     */
    private BigDecimal jackpotAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.first_prize
     *
     * @mbg.generated
     */
    private Integer firstPrize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.second_prize
     *
     * @mbg.generated
     */
    private Integer secondPrize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.third_prize
     *
     * @mbg.generated
     */
    private Integer thirdPrize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.fourth_prize
     *
     * @mbg.generated
     */
    private Integer fourthPrize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.fifth_prize
     *
     * @mbg.generated
     */
    private Integer fifthPrize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.sixth_prize
     *
     * @mbg.generated
     */
    private Integer sixthPrize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.seventh_prize
     *
     * @mbg.generated
     */
    private Integer seventhPrize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.add_time
     *
     * @mbg.generated
     */
    private LocalDateTime addTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lottery_daletou.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.id
     *
     * @return the value of lottery_daletou.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.id
     *
     * @param id the value for lottery_daletou.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.issue_number
     *
     * @return the value of lottery_daletou.issue_number
     *
     * @mbg.generated
     */
    public String getIssueNumber() {
        return issueNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.issue_number
     *
     * @param issueNumber the value for lottery_daletou.issue_number
     *
     * @mbg.generated
     */
    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.red_balls
     *
     * @return the value of lottery_daletou.red_balls
     *
     * @mbg.generated
     */
    public String getRedBalls() {
        return redBalls;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.red_balls
     *
     * @param redBalls the value for lottery_daletou.red_balls
     *
     * @mbg.generated
     */
    public void setRedBalls(String redBalls) {
        this.redBalls = redBalls;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.blue_balls
     *
     * @return the value of lottery_daletou.blue_balls
     *
     * @mbg.generated
     */
    public String getBlueBalls() {
        return blueBalls;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.blue_balls
     *
     * @param blueBalls the value for lottery_daletou.blue_balls
     *
     * @mbg.generated
     */
    public void setBlueBalls(String blueBalls) {
        this.blueBalls = blueBalls;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.draw_date
     *
     * @return the value of lottery_daletou.draw_date
     *
     * @mbg.generated
     */
    public LocalDateTime getDrawDate() {
        return drawDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.draw_date
     *
     * @param drawDate the value for lottery_daletou.draw_date
     *
     * @mbg.generated
     */
    public void setDrawDate(LocalDateTime drawDate) {
        this.drawDate = drawDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.jackpot_amount
     *
     * @return the value of lottery_daletou.jackpot_amount
     *
     * @mbg.generated
     */
    public BigDecimal getJackpotAmount() {
        return jackpotAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.jackpot_amount
     *
     * @param jackpotAmount the value for lottery_daletou.jackpot_amount
     *
     * @mbg.generated
     */
    public void setJackpotAmount(BigDecimal jackpotAmount) {
        this.jackpotAmount = jackpotAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.first_prize
     *
     * @return the value of lottery_daletou.first_prize
     *
     * @mbg.generated
     */
    public Integer getFirstPrize() {
        return firstPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.first_prize
     *
     * @param firstPrize the value for lottery_daletou.first_prize
     *
     * @mbg.generated
     */
    public void setFirstPrize(Integer firstPrize) {
        this.firstPrize = firstPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.second_prize
     *
     * @return the value of lottery_daletou.second_prize
     *
     * @mbg.generated
     */
    public Integer getSecondPrize() {
        return secondPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.second_prize
     *
     * @param secondPrize the value for lottery_daletou.second_prize
     *
     * @mbg.generated
     */
    public void setSecondPrize(Integer secondPrize) {
        this.secondPrize = secondPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.third_prize
     *
     * @return the value of lottery_daletou.third_prize
     *
     * @mbg.generated
     */
    public Integer getThirdPrize() {
        return thirdPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.third_prize
     *
     * @param thirdPrize the value for lottery_daletou.third_prize
     *
     * @mbg.generated
     */
    public void setThirdPrize(Integer thirdPrize) {
        this.thirdPrize = thirdPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.fourth_prize
     *
     * @return the value of lottery_daletou.fourth_prize
     *
     * @mbg.generated
     */
    public Integer getFourthPrize() {
        return fourthPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.fourth_prize
     *
     * @param fourthPrize the value for lottery_daletou.fourth_prize
     *
     * @mbg.generated
     */
    public void setFourthPrize(Integer fourthPrize) {
        this.fourthPrize = fourthPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.fifth_prize
     *
     * @return the value of lottery_daletou.fifth_prize
     *
     * @mbg.generated
     */
    public Integer getFifthPrize() {
        return fifthPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.fifth_prize
     *
     * @param fifthPrize the value for lottery_daletou.fifth_prize
     *
     * @mbg.generated
     */
    public void setFifthPrize(Integer fifthPrize) {
        this.fifthPrize = fifthPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.sixth_prize
     *
     * @return the value of lottery_daletou.sixth_prize
     *
     * @mbg.generated
     */
    public Integer getSixthPrize() {
        return sixthPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.sixth_prize
     *
     * @param sixthPrize the value for lottery_daletou.sixth_prize
     *
     * @mbg.generated
     */
    public void setSixthPrize(Integer sixthPrize) {
        this.sixthPrize = sixthPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.seventh_prize
     *
     * @return the value of lottery_daletou.seventh_prize
     *
     * @mbg.generated
     */
    public Integer getSeventhPrize() {
        return seventhPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.seventh_prize
     *
     * @param seventhPrize the value for lottery_daletou.seventh_prize
     *
     * @mbg.generated
     */
    public void setSeventhPrize(Integer seventhPrize) {
        this.seventhPrize = seventhPrize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.add_time
     *
     * @return the value of lottery_daletou.add_time
     *
     * @mbg.generated
     */
    public LocalDateTime getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.add_time
     *
     * @param addTime the value for lottery_daletou.add_time
     *
     * @mbg.generated
     */
    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.update_time
     *
     * @return the value of lottery_daletou.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.update_time
     *
     * @param updateTime the value for lottery_daletou.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lottery_daletou.deleted
     *
     * @return the value of lottery_daletou.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lottery_daletou.deleted
     *
     * @param deleted the value for lottery_daletou.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", IS_DELETED=").append(IS_DELETED);
        sb.append(", NOT_DELETED=").append(NOT_DELETED);
        sb.append(", id=").append(id);
        sb.append(", issueNumber=").append(issueNumber);
        sb.append(", redBalls=").append(redBalls);
        sb.append(", blueBalls=").append(blueBalls);
        sb.append(", drawDate=").append(drawDate);
        sb.append(", jackpotAmount=").append(jackpotAmount);
        sb.append(", firstPrize=").append(firstPrize);
        sb.append(", secondPrize=").append(secondPrize);
        sb.append(", thirdPrize=").append(thirdPrize);
        sb.append(", fourthPrize=").append(fourthPrize);
        sb.append(", fifthPrize=").append(fifthPrize);
        sb.append(", sixthPrize=").append(sixthPrize);
        sb.append(", seventhPrize=").append(seventhPrize);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LotteryDaletou other = (LotteryDaletou) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIssueNumber() == null ? other.getIssueNumber() == null : this.getIssueNumber().equals(other.getIssueNumber()))
            && (this.getRedBalls() == null ? other.getRedBalls() == null : this.getRedBalls().equals(other.getRedBalls()))
            && (this.getBlueBalls() == null ? other.getBlueBalls() == null : this.getBlueBalls().equals(other.getBlueBalls()))
            && (this.getDrawDate() == null ? other.getDrawDate() == null : this.getDrawDate().equals(other.getDrawDate()))
            && (this.getJackpotAmount() == null ? other.getJackpotAmount() == null : this.getJackpotAmount().equals(other.getJackpotAmount()))
            && (this.getFirstPrize() == null ? other.getFirstPrize() == null : this.getFirstPrize().equals(other.getFirstPrize()))
            && (this.getSecondPrize() == null ? other.getSecondPrize() == null : this.getSecondPrize().equals(other.getSecondPrize()))
            && (this.getThirdPrize() == null ? other.getThirdPrize() == null : this.getThirdPrize().equals(other.getThirdPrize()))
            && (this.getFourthPrize() == null ? other.getFourthPrize() == null : this.getFourthPrize().equals(other.getFourthPrize()))
            && (this.getFifthPrize() == null ? other.getFifthPrize() == null : this.getFifthPrize().equals(other.getFifthPrize()))
            && (this.getSixthPrize() == null ? other.getSixthPrize() == null : this.getSixthPrize().equals(other.getSixthPrize()))
            && (this.getSeventhPrize() == null ? other.getSeventhPrize() == null : this.getSeventhPrize().equals(other.getSeventhPrize()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssueNumber() == null) ? 0 : getIssueNumber().hashCode());
        result = prime * result + ((getRedBalls() == null) ? 0 : getRedBalls().hashCode());
        result = prime * result + ((getBlueBalls() == null) ? 0 : getBlueBalls().hashCode());
        result = prime * result + ((getDrawDate() == null) ? 0 : getDrawDate().hashCode());
        result = prime * result + ((getJackpotAmount() == null) ? 0 : getJackpotAmount().hashCode());
        result = prime * result + ((getFirstPrize() == null) ? 0 : getFirstPrize().hashCode());
        result = prime * result + ((getSecondPrize() == null) ? 0 : getSecondPrize().hashCode());
        result = prime * result + ((getThirdPrize() == null) ? 0 : getThirdPrize().hashCode());
        result = prime * result + ((getFourthPrize() == null) ? 0 : getFourthPrize().hashCode());
        result = prime * result + ((getFifthPrize() == null) ? 0 : getFifthPrize().hashCode());
        result = prime * result + ((getSixthPrize() == null) ? 0 : getSixthPrize().hashCode());
        result = prime * result + ((getSeventhPrize() == null) ? 0 : getSeventhPrize().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    public enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        private final Boolean value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public Boolean getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public Boolean value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "BIGINT", false),
        issueNumber("issue_number", "issueNumber", "VARCHAR", false),
        redBalls("red_balls", "redBalls", "VARCHAR", false),
        blueBalls("blue_balls", "blueBalls", "VARCHAR", false),
        drawDate("draw_date", "drawDate", "TIMESTAMP", false),
        jackpotAmount("jackpot_amount", "jackpotAmount", "DECIMAL", false),
        firstPrize("first_prize", "firstPrize", "INTEGER", false),
        secondPrize("second_prize", "secondPrize", "INTEGER", false),
        thirdPrize("third_prize", "thirdPrize", "INTEGER", false),
        fourthPrize("fourth_prize", "fourthPrize", "INTEGER", false),
        fifthPrize("fifth_prize", "fifthPrize", "INTEGER", false),
        sixthPrize("sixth_prize", "sixthPrize", "INTEGER", false),
        seventhPrize("seventh_prize", "seventhPrize", "INTEGER", false),
        addTime("add_time", "addTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        deleted("deleted", "deleted", "BIT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table lottery_daletou
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}