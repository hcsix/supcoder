package com.supcoder.lottery.mapper;

/**
 * LotteryDaletouMapper
 *
 * @author lee
 * @date 2024/12/13
 */
import java.util.List;

import com.supcoder.lottery.domain.LotteryDaletou;
import com.supcoder.lottery.domain.LotteryDaletouExample;
import org.apache.ibatis.annotations.Param;

public interface LotteryDaletouMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    long countByExample(LotteryDaletouExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    int deleteByExample(LotteryDaletouExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    int insert(LotteryDaletou record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    int insertSelective(LotteryDaletou record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    LotteryDaletou selectOneByExample(LotteryDaletouExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    LotteryDaletou selectOneByExampleSelective(@Param("example") LotteryDaletouExample example, @Param("selective") LotteryDaletou.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    List<LotteryDaletou> selectByExampleSelective(@Param("example") LotteryDaletouExample example, @Param("selective") LotteryDaletou.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    List<LotteryDaletou> selectByExample(LotteryDaletouExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    LotteryDaletou selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") LotteryDaletou.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    LotteryDaletou selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    LotteryDaletou selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LotteryDaletou record, @Param("example") LotteryDaletouExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LotteryDaletou record, @Param("example") LotteryDaletouExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LotteryDaletou record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LotteryDaletou record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LotteryDaletouExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_daletou
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Long id);
}