package cn.itcast.shop.utils;

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

import sun.net.smtp.SmtpClient;

/**
 * 邮件发送工具类
 * @author 传智.郭嘉
 *
 */
public class MailUitls {
	/**
	 * 发送邮件的方法
	 * @param to	:收件人
	 * @param code	:激活码
	 */
	public static void sendMail(String to,String code){
		/**
		 * 1.获得一个Session对象.
		 * 2.创建一个代表邮件的对象Message.
		 * 3.发送邮件Transport
		 */
		
	//972580752@qq.com向2024644008@qq.com发邮件

		
		// 1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		
		props.setProperty("mail.smtp.auth", "true");//必须 普通客户端
		
		props.setProperty("mail.transport.protocol", "smtp");//必须选择协议
		
		final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
		
	//	props.setProperty("mail.host", "localhost");
		
		Session session = Session.getInstance(props, new Authenticator() {

			//session.setDebug(true);//设置debug模式   在控制台看到交互信息
			
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("972580752@qq.com", "picoeygfbloubbgh");
			//	return new PasswordAuthentication("123@shop.com", "123");
			}
			
		});
		// 2.创建邮件对象:
		Message message = new MimeMessage(session);
		// 设置发件人:
		try {
			message.setFrom(new InternetAddress("972580752@qq.com"));
			//message.setFrom(new InternetAddress("123@shop.com"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 抄送 CC   密送BCC
			// 设置标题
			message.setSubject("来自购物天堂传智商城官方激活邮件");
			// 设置邮件正文:
			message.setContent("<h1>购物天堂传智商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://192.168.1.1:8080/shop/user_active.action?code="+code+"'>http://192.168.1.1:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		sendMail("2024644008@qq.com","11111111111111");
	//	sendMail("1@shop.com","11111111111111");
	}
}
