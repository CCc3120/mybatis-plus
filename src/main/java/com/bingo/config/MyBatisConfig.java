package com.bingo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;

@MapperScan("com.bingo.mapper")
@Configuration
public class MyBatisConfig {
	/**
	 * 分页插件
	 * 
	 * @return 分页插件的实例
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		// 防止total为0
		page.setDbType(DbType.MYSQL);
		// 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求 默认false
		page.setOverflow(false);
		// 设置最大单页限制数量，默认 500 条，-1 不受限制
		page.setLimit(500);
		// 开启 count 的 join 优化,只针对部分 left join
		page.setCountSqlParser(new JsqlParserCountOptimize(true));
		return page;
	}

	/**
     * 打印 sql
     */
	// @Bean
	// public PerformanceInterceptor performanceInterceptor() {
	// PerformanceInterceptor performanceInterceptor = new
	// PerformanceInterceptor();
	// //格式化sql语句
	// Properties properties = new Properties();
	// properties.setProperty("format", "true");
	// performanceInterceptor.setProperties(properties);
	// return performanceInterceptor;
	// }

	/**
	 * 乐观锁插件
	 * 
	 * @return 乐观锁插件的实例
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}
}
