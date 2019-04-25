package com.xuce.book.index.action;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xuce.book.category.service.CategoryService;
import com.xuce.book.category.vo.Category;
import com.xuce.book.product.service.ProductService;
import com.xuce.book.product.vo.Product;
/**
 * ��ҳ
 * @author Xuce
 *
 */
public class IndexAction extends ActionSupport{
	//ע��һ������service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//ע����Ʒservice
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String execute(){
		//��ѯ����һ������
//		List<Category> cList = categoryService.findAll();
			ActionContext.getContext().getSession().put("cList", categoryService.findAll());
		//��ѯ������Ʒ
			List<Product> hList = productService.findHot();
			//�����ݱ��浽ֵջ
			ActionContext.getContext().getValueStack().set("hList",hList);
			
			//��ѯ������Ʒ
			List<Product> nList = productService.findNew();
			ActionContext.getContext().getValueStack().set("nList",hList);
		return "index";
	}
}
