package com.xuce.book.index.action;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xuce.book.category.service.CategoryService;
import com.xuce.book.category.vo.Category;
import com.xuce.book.product.service.ProductService;
import com.xuce.book.product.vo.Product;
/**
 * 首页
 * @author Xuce
 *
 */
public class IndexAction extends ActionSupport{
	//注入一级分类service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//注入商品service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String execute(){
		//查询所有一级分类
//		List<Category> cList = categoryService.findAll();
			ActionContext.getContext().getSession().put("cList", categoryService.findAll());
		//查询热门商品
			List<Product> hList = productService.findHot();
			//将数据保存到值栈
			ActionContext.getContext().getValueStack().set("hList",hList);
			
			//查询最新商品
			List<Product> nList = productService.findNew();
			ActionContext.getContext().getValueStack().set("nList",hList);
		return "index";
	}
}
