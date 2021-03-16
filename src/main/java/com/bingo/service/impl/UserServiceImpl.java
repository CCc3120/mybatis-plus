package com.bingo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.UserMapper;
import com.bingo.model.User;
import com.bingo.service.UserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bingo
 * @since 2021-03-11
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;
}
