package code.dao;

import org.springframework.stereotype.Repository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Knigh on 2016/11/13.
 */
@Repository
public class EmailDao extends BaseDao{
    //邮件发送协议
    private final String PROTOCOL = "smtp";
    //from(发送方)的smtp邮件服务器(这个东西可以在sina邮箱设置的客户端pop/imap/smtp中查看)
    private final static String HOST = "smtp.163.com";
    //smtp邮件服务器默认端口
    private final String PORT = "25";
    //是否要求身份认证
    private final static String IS_AUTH = "true";
    //是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private final String IS_ENABLED_DEBUG_MOD = "true";
    //发件人
    private static final String from = "m18702510536_3@163.com";
    //收件人
    private final String to = "1990785022@qq.com";
    //初始化连接邮件服务器的会话信息
    private static Properties properties = null;

    {
        properties = new Properties();
        properties.setProperty("mail.transport.protocol", PROTOCOL);
        properties.setProperty("mail.smtp.host", HOST);
        properties.setProperty("mail.smtp.port", PORT);
        properties.setProperty("mail.smtp.auth", IS_AUTH);
        properties.setProperty("mail.debug", IS_ENABLED_DEBUG_MOD);
    }


    /**
     * 发送简单的文本文件
     * @param mes
     * @throws Exception
     */
    public void sentTextEmail(String mes){
        try {
            //创建session实例对象
            Session session = Session.getDefaultInstance(properties);
            //创建MimeMessage实例对象
            MimeMessage message = new MimeMessage(session);
            //设置发件人
            message.setFrom(new InternetAddress(from));
            //设置邮件主题
            message.setSubject("CSDN Input Over:["+ (new SimpleDateFormat("yyyy-mm-dd hh:mm:ss")).format(new Date())+"]");
            //设置收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //设置发送时间
            message.setSentDate(new Date());
            //设置纯文本内容为邮件正文
            message.setText(mes);
            //保存并生成最终的邮件内容
            message.saveChanges();
            //获得transport实例对象
            Transport transport = session.getTransport();
            //打开连接
            //如果是网易邮箱，对应的是登录名、授权码
            transport.connect("18702510536", "231029");
            //将message对象传递给transport对象，将邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            //关闭连接
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static class MyAuthenticator extends Authenticator {
        private String username = "18702510536";
                private String password = "lw231029";
//        private String password = "231029";

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    /**
     * 发送html邮件
     */
    public void sendHtmlEmail(String mes){
        try {
            //创建Session实例对象
            Session session = Session.getInstance(properties, new MyAuthenticator());
            //创建MimeMessage实例对象
            MimeMessage message = new MimeMessage(session);
            //设置邮件主题
            message.setSubject("CSDN Input Over:["+ (new SimpleDateFormat("yyyy-mm-dd hh:mm:ss")).format(new Date())+"]");
            //设置发送人
            message.setFrom(new InternetAddress(from));
            //设置发送时间
            message.setSentDate(new Date());
            //设置收件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
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
