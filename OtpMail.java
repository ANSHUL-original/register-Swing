package register;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OtpMail {

	public OtpMail(String email,String OTP) {
		// TODO Auto-generated constructor stub
		final String username="mightydoop@gmail.com";
		final String password="23audi32";
		
		String remail=email;
		String subject="OTP-alumNetwork";
		String msg=OTP+" is your otp\nDont share it to anybody.";
		
		Properties pros=new Properties();
		pros.put("mail.smtp.host", "smtp.gmail.com");
		pros.put("mail.smtp.auth", "true");
		pros.put("mail.debug", "true");
		
		pros.put("mail.smtp.port", "465");
		pros.put("mail.smtp.socketFactory.port", "465");
		pros.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		pros.put("mail.smtp.socketFactory.fallback", "false");
		
		Session session=Session.getInstance(pros, new Authenticator() {
		
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, password);
			}
		
		});
		try {
			Message ms=new MimeMessage(session);
			ms.setFrom(new InternetAddress(username));
			
			ms.setRecipients(Message.RecipientType.TO, InternetAddress.parse(remail));
			
			ms.setSubject(subject);
			ms.setText(msg);
			Transport.send(ms);
			
			System.out.println("email send");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("NOT send");
		}
	}
}