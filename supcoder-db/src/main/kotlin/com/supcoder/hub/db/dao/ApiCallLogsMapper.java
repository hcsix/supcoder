package com.supcoder.hub.db.dao;

import com.supcoder.hub.db.domain.ApiCallLogs;
import com.supcoder.hub.db.domain.ApiCallLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApiCallLogsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    long countByExample(ApiCallLogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    int deleteByExample(ApiCallLogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    int insert(ApiCallLogs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    int insertSelective(ApiCallLogs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    ApiCallLogs selectOneByExample(ApiCallLogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    ApiCallLogs selectOneByExampleSelective(@Param("example") ApiCallLogsExample example, @Param("selective") ApiCallLogs.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    List<ApiCallLogs> selectByExampleSelective(@Param("example") ApiCallLogsExample example, @Param("selective") ApiCallLogs.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    List<ApiCallLogs> selectByExample(ApiCallLogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    ApiCallLogs selectByPrimaryKeySelective(@Param("logId") Integer logId, @Param("selective") ApiCallLogs.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    ApiCallLogs selectByPrimaryKey(Integer logId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ApiCallLogs record, @Param("example") ApiCallLogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ApiCallLogs record, @Param("example") ApiCallLogsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ApiCallLogs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_call_logs
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ApiCallLogs record);
}