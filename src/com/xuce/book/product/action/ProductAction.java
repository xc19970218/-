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
	//根据categoryid查询商品
	public String findByCid(){
		PageBean<Product> pageBean = productService.findByPageId(cid,page);//分页查询
		System.out.println(cid+"+++++++++++++++");
		System.out.println(pageBean.getTotalCount()+"=====================");
		//将pageBean存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	//二级分类查询
	public String findByCsid(){
		PageBean<Product> pageBean =productService.findPageByCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
