package com.bingo.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value = "sl_id", type = IdType.ASSIGN_UUID)
	private String slId;

	private String slName;

	private Integer slAge;

	/**
	 * 非数据库字段
	 */
	@TableField(exist = false)
	private Address slAddress;

	/**
	 * 自动填充
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date slUpdateTime;

	/**
	 * 逻辑删除（0 未删除、1 删除）
	 */
	@TableLogic(value = "0", delval = "1")
	@TableField(fill = FieldFill.INSERT)
	private Integer slIsAvailable;

	/**
	 * 版本号（用于乐观锁， 默认为 1）
	 */
	@Version
	@TableField(fill = FieldFill.INSERT)
	private Integer version;

}
