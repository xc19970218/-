package com.xuce.book.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xuce.book.product.dao.ProductDao;
import com.xuce.book.product.vo.Product;
import com.xuce.book.utils.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}

	public List<Product> findNew() {
		
		return productDao.findNew();
	}

	public Product findById(Integer pid) {
		return productDao.findByName(pid);
	}

	public PageBean<Product> findByPageId(Integer cid, int page) {
		PageBean<Product> pageBean =new PageBean<Product>();
		int limit =8;
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		int totalCount =0;
		totalCount = productDao.findCountId(cid);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount/limit);
		pageBean.setTotalPage(totalPage);
		//
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
		// TODO Auto-generated method stub
		
	}

	public  PageBean<Product> findPageByCsid(Integer csid, int page) {
		PageBean<Product> pageBean =new PageBean<Product>();
		int limit =8;
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		int totalCount =0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
//		int totalPage = (int) Math.ceil(totalCount/limit);
//		pageBean.setTotalPage(totalPage);
		// 设置总页数:
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//
		int begin = (page-1)*limit;
		List<Product> list = productDao.findPageByCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//查询商品
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		productDao.save(product);
	}


	
}
