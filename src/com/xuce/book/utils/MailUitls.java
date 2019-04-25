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
 * �����ʼ�
 * @author Xuce
 *
 */
public class MailUitls {
	/**
	 * �����ʼ��ķ���
	 * @param to		:�ռ���
	 * @param code		:������
	 */
	public static void sendEamil(String to,String code){
			/**
			 * 1.����һ��session����
			 * 2.����һ�������ʼ��Ķ���message
			 * 3.�����ʼ�transport
			 */
		//1 ��ȡ���Ӷ���
		Properties props = new Properties();
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("xuce@shop.com","xuce");
			}
			
		});
		// 2.�����ʼ�����:
				Message message = new MimeMessage(session);
				// ���÷�����:
				try {
					message.setFrom(new InternetAddress("service@shop.com"));
					// �����ռ���:
					message.addRecipient(RecipientType.TO, new InternetAddress(to));
					// ���� CC   ����BCC
					// ���ñ���
					message.setSubject("����������ǹٷ������ʼ�");
					// �����ʼ�����:
					message.setContent("<h1>������ǹٷ������ʼ�!������������ɼ������!</h1><h3><a href='http://129.28.124.31:8080/shop/user_active.action?code="+code+"'>http://129.28.124.31:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
					// 3.�����ʼ�:
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

