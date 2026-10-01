package com.huyanh.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil_22145150 {
    // >>> SỬA: email gửi + "Mật khẩu ứng dụng" (App Password) 16 ký tự của Gmail (bật xác minh 2 bước mới tạo được)
    private static final String FROM = "huynhanhhuyvl2007@gmail.com";
    private static final String APP_PASSWORD = "nprbmpdajzshlxio";

    public static boolean send(String to, String subject, String content) {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.ssl.protocols", "TLSv1.2");
            Session session = Session.getInstance(p, new Authenticator() {
                @Override protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM, APP_PASSWORD);
                }
            });
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject, "UTF-8");
            msg.setText(content, "UTF-8");
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
