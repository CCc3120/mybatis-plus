package com.bingo.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@RequestMapping(value = "/testDataSource")
	public String testDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(
				"jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
		druidDataSource.setUsername("root");
		druidDataSource.setPassword("root");
		try {
			DruidPooledConnection connection = druidDataSource.getConnection();
			return "true";
		} catch (SQLException e) {
			e.printStackTrace();
			return "false";
		}
	}
}
