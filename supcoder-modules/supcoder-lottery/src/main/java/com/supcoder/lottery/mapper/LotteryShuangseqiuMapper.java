package com.supcoder.lottery.mapper;

/**
 * LotteryShuangseqiuMapper
 *
 * @author lee
 * @date 2024/12/13
 */
import java.util.List;

import com.supcoder.lottery.domain.LotteryShuangseqiu;
import com.supcoder.lottery.domain.LotteryShuangseqiuExample;
import org.apache.ibatis.annotations.Param;

public interface LotteryShuangseqiuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    long countByExample(LotteryShuangseqiuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    int deleteByExample(LotteryShuangseqiuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    int insert(LotteryShuangseqiu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    int insertSelective(LotteryShuangseqiu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    LotteryShuangseqiu selectOneByExample(LotteryShuangseqiuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    LotteryShuangseqiu selectOneByExampleSelective(@Param("example") LotteryShuangseqiuExample example, @Param("selective") LotteryShuangseqiu.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    List<LotteryShuangseqiu> selectByExampleSelective(@Param("example") LotteryShuangseqiuExample example, @Param("selective") LotteryShuangseqiu.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    List<LotteryShuangseqiu> selectByExample(LotteryShuangseqiuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    LotteryShuangseqiu selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") LotteryShuangseqiu.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    LotteryShuangseqiu selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    LotteryShuangseqiu selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LotteryShuangseqiu record, @Param("example") LotteryShuangseqiuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LotteryShuangseqiu record, @Param("example") LotteryShuangseqiuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LotteryShuangseqiu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LotteryShuangseqiu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LotteryShuangseqiuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lottery_shuangseqiu
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Long id);
}
