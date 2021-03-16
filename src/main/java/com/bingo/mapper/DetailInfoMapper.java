package com.bingo.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bingo.model.DetailInfo;

public interface DetailInfoMapper extends BaseMapper<DetailInfo> {

	@Results({
			@Result(id = true, column = "sl_id", property = "slId"),
			@Result(column = "sl_name", property = "slName")
	})
	@Select("select * from detail_info where sl_id = #{slId}")
	public DetailInfo selectByPrimaryKey(String slId);
}
