package com.xuce.book.user.service;

import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.xuce.book.user.dao.UserDao;
import com.xuce.book.user.vo.User;
import com.xuce.book.utils.UUIDUtils;

/**
 * �û�ҵ���
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
	 * ��ѯ�û��� ����userdao
	 */
	public User findByName(String username){
		return userDao.findByName(username);
	}


	public void save(User user) {
		//�������ݿ�
		String code = new UUIDUtils().getUUID();
		user.setState(0); //0 �Ѽ��� 1 δ����
		user.setCode(code);//������
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	//�û���¼
	public User login(User user) {
		// TODO Auto-generated method stub
		System.out.println(user.getUsername()+"��¼��");
		return userDao.login(user);
	}
	
}
