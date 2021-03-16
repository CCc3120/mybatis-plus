package com.bingo.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.model.Address;

public interface AddressMapper extends BaseMapper<Address> {

	@Results({
			@Result(id = true, column = "sl_id", property = "slId"),
			@Result(column = "sl_name", property = "slName"),
			@Result(column = "sl_deteil_id", property = "slDetailInfo", one = @One(fetchType = FetchType.LAZY, select = "com.bingo.mapper.DetailInfoMapper.selectByPrimaryKey"))
	})
	@Select("select * from address where sl_id = #{slId}")
	public Address selectByPrimaryKey(String slId);
}
