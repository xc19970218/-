package com.xuce.book.user.dao;

import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xuce.book.user.vo.User;

/**
 * �û��־ò�
 * @author Xuce
 *
 */
public class UserDao extends HibernateDaoSupport{

	public User findByName(String username){
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql,username);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	//ע���û� �������ݿ�
	public void save(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}
	//�û���¼
	public User login(User user) {
		String hql ="from User where username= ? and password= ? and state = ?";
		System.out.println("loginDao�еĵ�¼��:"+user.getUsername());
		List<User> list = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),0);
		if(list.size()>0 && list!=null){
			System.out.println(list.get(0).getUsername()+"============");
			return list.get(0);
		}
		return null;
	}
}