package com.xuce.book.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xuce.book.product.vo.Product;
import com.xuce.book.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		//ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot",1));
		criteria.addOrder(Order.desc("pdate"));
		List<Product> plist = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return plist;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> nlist = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return nlist;
	}

	public int findCountId(Integer cid) {
		String hqlString = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hqlString,cid);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}else {
			return 0;
		}
	}

	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		//select p from Category c,CategorySecond,Product p where c.cid = cs.category.cid and 
		//cs.cid = p.categroySecond.cid and c.cid =?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		//��ҳ
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid},begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public int findCountCsid(Integer csid) {
		String hql ="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		return list.get(0).intValue();
	}

	public List<Product> findPageByCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid =?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[]{csid},begin,limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	public Product findByName(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	public void save(Product product) {
		this.getHibernateTemplate().save(product);
		//保存完毕 跳转
	} 

}
