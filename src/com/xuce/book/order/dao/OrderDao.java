package com.xuce.book.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xuce.book.order.vo.Order;
import com.xuce.book.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{

	public int findCount() {
		String hql = "select count(*) from Orders";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Orders by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}

}
