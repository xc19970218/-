package com.xuce.book.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * 发送邮件
 * @author Xuce
 *
 */
public class MailUitls {
	/**
	 * 发送邮件的方法
	 * @param to		:收件人
	 * @param code		:激活码
	 */
	public static void sendEamil(String to,String code){
			/**
			 * 1.创建一个session对象
			 * 2.创建一个代表邮件的对象message
			 * 3.发送邮件transport
			 */
		//1 获取连接对象
		Properties props = new Properties();
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("xuce@shop.com","xuce");
			}
			
		});
		// 2.创建邮件对象:
				Message message = new MimeMessage(session);
				// 设置发件人:
				try {
					message.setFrom(new InternetAddress("service@shop.com"));
					// 设置收件人:
					message.addRecipient(RecipientType.TO, new InternetAddress(to));
					// 抄送 CC   密送BCC
					// 设置标题
					message.setSubject("来自老徐书城官方激活邮件");
					// 设置邮件正文:
					message.setContent("<h1>老徐书城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://129.28.124.31:8080/shop/user_active.action?code="+code+"'>http://129.28.124.31:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
					// 3.发送邮件:
					Transport.send(message);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				
			}
			
			public static void main(String[] args) {
				sendEamil("aaa@shop.com","aaa");
			}
}

