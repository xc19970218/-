package com.xuce.book.categorySecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xuce.book.categorySecond.dao.SecondDao;
import com.xuce.book.categorySecond.vo.CategorySecond;
import com.xuce.book.utils.PageBean;


/**
 * 二级分类的业务层代码
 * 
 * @author xc
 * 
 */
@Transactional
public class SecondService {
	// 注入Dao
	private SecondDao secondDao;

	public void setCategorySecondDao(SecondDao secondDao) {
		this.secondDao = secondDao;
	}

	// 二级分类带有分页的查询操作:
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();

		// 设置参数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = secondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置页面显示数据的集合:
		int begin = (page - 1) * limit;
		List<CategorySecond> list = secondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 业务层的保存二级分类的操作
	public void save(CategorySecond categorySecond) {
		secondDao.save(categorySecond);
	}

	// 业务层的删除二级分类的操作
	public void delete(CategorySecond categorySecond) {
		secondDao.delete(categorySecond);
	}

	// 业务层根据id查询二级分类
	public CategorySecond findByCsid(Integer csid) {
		return secondDao.findByCsid(csid);
	}

	// 业务层修改二级分类的方法
	public void update(CategorySecond categorySecond) {
		secondDao.update(categorySecond);
	}

	// 查询所有二级分类
	public List<CategorySecond> findAll() {
		return secondDao.findAll();
	}

}
