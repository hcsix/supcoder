package com.supcoder.lottery.mapper;

/**
 * LotteryTypeMapper
 *
 * @author lee
 * @date 2024/12/13
 */
import java.util.List;

import com.supcoder.lottery.domain.LotteryType;
import com.supcoder.lottery.domain.LotteryTypeExample;
import org.apache.ibatis.annotations.Param;

public interface LotteryTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    long countByExample(LotteryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    int deleteByExample(LotteryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    int insert(LotteryType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    int insertSelective(LotteryType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    LotteryType selectOneByExample(LotteryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    LotteryType selectOneByExampleSelective(@Param("example") LotteryTypeExample example, @Param("selective") LotteryType.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    List<LotteryType> selectByExampleSelective(@Param("example") LotteryTypeExample example, @Param("selective") LotteryType.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    List<LotteryType> selectByExample(LotteryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    LotteryType selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LotteryType.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    LotteryType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    LotteryType selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LotteryType record, @Param("example") LotteryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LotteryType record, @Param("example") LotteryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LotteryType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LotteryType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LotteryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_type
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}