package com.xuce.book.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xuce.book.user.service.UserService;
import com.xuce.book.user.vo.User;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	private User user = new User();
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 注册页面方法
	 */
	public String registPage(){
		return "registPage";
	}
	
	/**
	 * 异步校验用户名
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		/**
		 * 调用service
		 */
		System.out.println(user.getUsername());
		User existsUser = userService.findByName(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existsUser!=null){
			//用户名已存在 不能注册 
			response.getWriter().print("<font color='red'>用户名已存在 </font>");
			System.out.println(existsUser.getName()+"-----------========");
		}else{
			//用户名不存在 可以注册
			response.getWriter().print("<font color='green'>用户名未注册</font>");
		}
		return NONE;
	}
	/**
	 * 注册成功
	 * @return
	 */
	public String regist(){
		//从session中获得验证码
		String truecode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!truecode.equalsIgnoreCase(checkcode)){
			this.addActionError("验证码8正确");
			return "ckcodeFail";
		}
		userService.save(this.user);
		System.out.println(this.user.getUsername()+"000000000");
		this.addActionMessage("注册成功,欢迎使用本书城!");
		return "msg";
	}

	
	/**
	 * 跳转登录页面
	 * @return
	 */
	public String loginPage(){
		return "loginPage";
	}

	/**
	 * 登录方法
	 * @return
	 */
	public String login(){
		User existUser = userService.login(user);
		if(existUser==null){
			//登陆失败
			this.addActionError("登陆失败,用户名或密码错误");
			return "login";
		}else {
			//登陆成功
			//1 将的登录信息存在session中
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//2 跳转
			return "loginSuccess";
		}
	}

	/**
	 * 退出方法
	 * @return
	 */
	public String quit(){
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
