package com.xuce.book.user.service;

import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.xuce.book.user.dao.UserDao;
import com.xuce.book.user.vo.User;
import com.xuce.book.utils.UUIDUtils;

/**
 * 用户业务层
 * @author Xuce
 *
 */
@Transactional
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	/**
	 * 查询用户名 调用userdao
	 */
	public User findByName(String username){
		return userDao.findByName(username);
	}


	public void save(User user) {
		//存入数据库
		String code = new UUIDUtils().getUUID();
		user.setState(0); //0 已激活 1 未激活
		user.setCode(code);//激活码
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	//用户登录
	public User login(User user) {
		// TODO Auto-generated method stub
		System.out.println(user.getUsername()+"登录名");
		return userDao.login(user);
	}
	
}
