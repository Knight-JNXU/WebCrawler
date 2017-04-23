package code.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Knigh on 2016/11/13.
 */
@Component
public class EmailUtils {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmailUtils.class);

    //邮件发送协议
    @Value("#{emailConf.PROTOCOL}")
    private String PROTOCOL;
    //fromEmail(发送方)的smtp邮件服务器(这个东西可以在sina邮箱设置的客户端pop/imap/smtp中查看)
    @Value("#{emailConf.HOST}")
    private String HOST;
    //smtp邮件服务器默认端口
    @Value("#{emailConf.PORT}")
    private String PORT;
    //是否要求身份认证
    @Value("#{emailConf.IS_AUTH}")
    private String IS_AUTH;
    //是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    @Value("#{emailConf.IS_ENABLED_DEBUG_MOD}")
    private String IS_ENABLED_DEBUG_MOD;
    //发件人
    @Value("#{emailConf.fromEmail}")
    private String fromEmail;
    //收件人
    @Value("#{emailConf.toEmail}")
    private String toEmail;

    @Value("#{emailConf.emailUserName}")
    private String emailUserName;
    @Value("#{emailConf.emailPassword}")
    private String emailPassword;

    //初始化连接邮件服务器的会话信息
    private Properties properties = null;

    private void initializeProperties(){
        LOGGER.info("initialize  properties!");
        properties = new Properties();
        properties.setProperty("mail.transport.protocol", PROTOCOL);
        properties.setProperty("mail.smtp.host", HOST);
        properties.setProperty("mail.smtp.port", PORT);
        properties.setProperty("mail.smtp.auth", IS_AUTH);
        properties.setProperty("mail.debug", IS_ENABLED_DEBUG_MOD);
        LOGGER.info("initialize  properties over!");
    }

    private class MyAuthenticator extends Authenticator {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(emailUserName, emailPassword);
        }
    }

    /**
     * 发送html邮件
     */
    public void sendHtmlEmail(String mes){
        if(null==properties){
            initializeProperties();
        }
        try {
            //创建Session实例对象
            Session session = Session.getInstance(properties, new MyAuthenticator());
            //创建MimeMessage实例对象
            MimeMessage message = new MimeMessage(session);
            //设置邮件主题
            message.setSubject("CSDN Input Over:["+ (new SimpleDateFormat("yyyy-mm-dd hh:mm:ss")).format(new Date())+"]");
            //设置发送人
            message.setFrom(new InternetAddress(fromEmail));
            //设置发送时间
            message.setSentDate(new Date());
            //设置收件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为utf-8
            message.setContent("<h3>"+mes+"</h3>", "text/html;charset=utf-8");
            //保存并生成最终的邮件内容
            message.saveChanges();
            //发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
