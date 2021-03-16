package com.bingo.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.bingo.model.User;

public interface UserMapper extends BaseMapper<User> {

	public User selectRelation(String slId);

	@Results({
			@Result(id = true, column = "sl_id", property = "slId"),
			@Result(column = "sl_name", property = "slName"),
			@Result(column = "sl_age", property = "slAge"),
			@Result(column = "sl_update_time", property = "slUpdateTime"),
			@Result(column = "sl_is_available", property = "slIsAvailable"),
			@Result(column = "version", property = "version"),
			@Result(column = "sl_address_id", property = "slAddress", one = @One(fetchType = FetchType.LAZY,select = "com.bingo.mapper.AddressMapper.selectById"))
	}) // fetchType = FetchType.LAZY,
	@Select("select * from user where sl_id = #{slId}")
	public User selectByPrimaryKey(String slId);

	/**
	 * 若使用QueryWrapper条件查询，需要增加${ew.customSqlSegment}
	 * 
	 * @param page
	 * @param queryWrapper
	 * @return
	 */
	@Results({
			@Result(id = true, column = "sl_id", property = "slId"),
			@Result(column = "sl_name", property = "slName"),
			@Result(column = "sl_age", property = "slAge"),
			@Result(column = "sl_update_time", property = "slUpdateTime"),
			@Result(column = "sl_is_available", property = "slIsAvailable"),
			@Result(column = "version", property = "version"),
			@Result(column = "sl_address_id", property = "slAddress", one = @One(fetchType = FetchType.LAZY, select = "com.bingo.mapper.AddressMapper.selectByPrimaryKey"))
	})
	@Select("select * from user ${ew.customSqlSegment} ")
	public IPage<User> selectByPage(IPage<User> page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);

}
