package com.bingo.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * 自动填充创建时间/修改时间
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.strictInsertFill(metaObject, "slCreateTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "slUpdateTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "slIsAvailable", Integer.class, 0);
		this.strictInsertFill(metaObject, "version", Integer.class, 1);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "slUpdateTime", Date.class, new Date());
	}

}
