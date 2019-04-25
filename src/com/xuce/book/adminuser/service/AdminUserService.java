package com.xuce.book.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import com.xuce.book.adminuser.dao.AdminUserDao;
import com.xuce.book.adminuser.vo.AdminUser;

@Transactional
public class AdminUserService {
	// 注入Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
