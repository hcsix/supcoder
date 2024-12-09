package com.supcoder.core.mapper

import com.supcoder.core.model.domain.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * UserMapper
 *
 * @author lee
 * @date 2024/12/9
 */

@Mapper
interface UserMapper {
    @Select("SELECT * FROM users WHERE username = #{username}")
    fun findByUsername(@Param("username") username:String):User
}
