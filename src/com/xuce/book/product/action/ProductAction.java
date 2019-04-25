package com.xuce.book.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xuce.book.category.service.CategoryService;
import com.xuce.book.product.service.ProductService;
import com.xuce.book.product.vo.Product;
import com.xuce.book.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	private Integer csid;
	
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	private Product product = new Product();
	public Product getModel() {
		return product;
	}
	private Integer cid;
	private CategoryService categoryService;
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String findByPid(){
		//System.out.println(product.getPid()+"======================");
		product = productService.findById(product.getPid());
		return "findById";
	}
	//����categoryid��ѯ��Ʒ
	public String findByCid(){
		PageBean<Product> pageBean = productService.findByPageId(cid,page);//��ҳ��ѯ
		System.out.println(cid+"+++++++++++++++");
		System.out.println(pageBean.getTotalCount()+"=====================");
		//��pageBean����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	//���������ѯ
	public String findByCsid(){
		PageBean<Product> pageBean =productService.findPageByCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
