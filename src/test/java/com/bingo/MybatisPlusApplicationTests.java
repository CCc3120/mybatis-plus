package com.bingo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bingo.mapper.UserMapper;
import com.bingo.model.User;
import com.bingo.service.UserService;

@SpringBootTest
class MybatisPlusApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	// private static final ThreadLocal<String> dataSourceKey =
	// ThreadLocal.withInitial(() -> "defaultDataSource");
	//
	// public static void main(String[] args) {
	// System.out.println(dataSourceKey.get());
	// dataSourceKey.set("qdscasdas");
	// System.out.println(dataSourceKey.get());
	// dataSourceKey.remove();
	// System.out.println(dataSourceKey.get());
	// }

	@Test
	void dataSource() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		// queryWrapper.eq("sl_name", "小明180629");

		Page<User> page = new Page<User>();
		userMapper.selectByPage(page, queryWrapper);
		page.getRecords().forEach(obj -> {
			if (obj.getSlAddress() != null) {
				System.out.println(obj.getSlAddress().getSlDetailInfo());
			}
		});
		setDataSource();
		System.out.println("切换数据源成功---------------------------");

		QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
		// queryWrapper.eq("sl_name", "小明180629");

		Page<User> page1 = new Page<User>();
		userMapper.selectByPage(page1, queryWrapper1);
		page1.getRecords().forEach(obj -> {
			if (obj.getSlAddress() != null) {
				System.out.println(obj.getSlAddress().getSlDetailInfo());
			}
		});

	}

	@Test
	void setDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(
				"jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
		druidDataSource.setUsername("root");
		druidDataSource.setPassword("root");
		try {
			DruidPooledConnection connection = druidDataSource.getConnection();
			System.out.println(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DynamicDataSource.dataSourcesMap.put("dbkey", druidDataSource);
		// DynamicDataSource.setDataSource("dbkey");
		// 此时数据源已切换到druidDataSource ，调用自己的业务方法即可。
		// 使用完后调用DynamicDataSource.clear();重置为默认数据源。
	}

	@Test
	void testRelation() {
		// User u = userService.getById("33e09ca27b5feaf285028807bb1a7612");
		// User u =
		// userMapper.selectByPrimaryKey("00005ab8f7faa5138d87ae0c0353fd5f");
		// u.getSlName();
		// System.out.println(u.getSlAddress());

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		// queryWrapper.eq("sl_name", "小明180629");

		Page<User> page = new Page<User>();
		userMapper.selectByPage(page, queryWrapper);
		page.getRecords().forEach(obj -> {
			if (obj.getSlAddress() != null) {
				System.out.println(obj.getSlAddress().getSlDetailInfo());
			}
		});

	}

	@Test
	void testAdd() {
		List<User> list = new ArrayList<User>();
		Random r = new Random();
		for (int i = 100000; i < 1000000; i++) {
			User user = new User();
			user.setSlName("小明" + i);
			user.setSlAge(r.nextInt(100));
			list.add(user);
		}
		userService.saveBatch(list);
		// Random r = new Random();
		// for (int i = 0; i < 1000; i++) {
		// User user = new User();
		// user.setSlName("小明" + i);
		// user.setSlAge(r.nextInt(100));
		// userMapper.insert(user);
		// }
	}

	@Test
	void testDelete() {
		userService.removeById("00000f9b197941dc08977fab9dccf91a");
	}

	@Test
	void contextLoads() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userService.list();
		for (User user : userList) {
			System.out.println(user);
		}
	}

	@Test
	void testPage() {
		Page<User> page = new Page<User>();
		// List<OrderItem> orders = new ArrayList<OrderItem>();
		// orders.add(OrderItem.asc("slName"));
		// page.setOrders(orders);
		userService.page(page);

		page.getRecords().forEach(System.out::println);
	}

	@Test
	void testVersion() {
		// 乐观锁
		User user = new User();
		user.setSlName("xiaohong1");
		user.setSlAge(22);
		userService.save(user);

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sl_name", "xiaohong1");
		User u = userService.getOne(queryWrapper);

		System.out.println(u);
		u.setSlAge(18);

		userService.saveOrUpdate(u);

		QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
		queryWrapper1.eq("sl_name", "xiaohong1");
		User u2 = userService.getOne(queryWrapper1);
		System.out.println(u2);
	}

	@Test
	void testQueryWrapper() {
		// Step1：创建一个 QueryWrapper 对象
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();

		// Step2： 构造查询条件
		queryWrapper
				.select("sl_id", "sl_name", "sl_age")
				.eq("sl_age", 20)
		// .like("name", "")
		;

		// Step3：执行查询
		userService
				.list(queryWrapper)
				.forEach(System.out::println);
	}
}
