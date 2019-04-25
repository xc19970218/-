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
	 * ע��ҳ�淽��
	 */
	public String registPage(){
		return "registPage";
	}
	
	/**
	 * �첽У���û���
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		/**
		 * ����service
		 */
		System.out.println(user.getUsername());
		User existsUser = userService.findByName(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existsUser!=null){
			//�û����Ѵ��� ����ע�� 
			response.getWriter().print("<font color='red'>�û����Ѵ��� </font>");
			System.out.println(existsUser.getName()+"-----------========");
		}else{
			//�û��������� ����ע��
			response.getWriter().print("<font color='green'>�û���δע��</font>");
		}
		return NONE;
	}
	/**
	 * ע��ɹ�
	 * @return
	 */
	public String regist(){
		//��session�л����֤��
		String truecode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!truecode.equalsIgnoreCase(checkcode)){
			this.addActionError("��֤��8��ȷ");
			return "ckcodeFail";
		}
		userService.save(this.user);
		System.out.println(this.user.getUsername()+"000000000");
		this.addActionMessage("ע��ɹ�,��ӭʹ�ñ����!");
		return "msg";
	}

	
	/**
	 * ��ת��¼ҳ��
	 * @return
	 */
	public String loginPage(){
		return "loginPage";
	}

	/**
	 * ��¼����
	 * @return
	 */
	public String login(){
		User existUser = userService.login(user);
		if(existUser==null){
			//��½ʧ��
			this.addActionError("��½ʧ��,�û������������");
			return "login";
		}else {
			//��½�ɹ�
			//1 ���ĵ�¼��Ϣ����session��
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			//2 ��ת
			return "loginSuccess";
		}
	}

	/**
	 * �˳�����
	 * @return
	 */
	public String quit(){
		//����session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
