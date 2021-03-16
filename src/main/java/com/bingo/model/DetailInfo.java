package com.bingo.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailInfo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@TableId(value = "sl_id", type = IdType.ASSIGN_UUID)
	private String slId;

	private String slName;

}
