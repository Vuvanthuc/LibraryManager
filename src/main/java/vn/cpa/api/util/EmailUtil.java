package vn.cpa.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import vn.cpa.api.config.Constant;
import vn.cpa.api.utility.PropertiesUtil;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Properties;
import java.util.Queue;

public class EmailUtil implements Runnable {

    private static Logger log = LoggerFactory.getLogger(EmailUtil.class);

    private static EmailUtil INSTANCE = null;

    private SmtpAuthenticator smtpAuthenticator;
    private Queue<MailDto> mailDtoQueue;

    public static EmailUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmailUtil();
            new Thread(INSTANCE).start();
        }
        return INSTANCE;
    }

    public EmailUtil() {
        String email = PropertiesUtil.getEmailProperty("mail.user");
        String password = PropertiesUtil.getEmailProperty("mail.password");
        smtpAuthenticator = new SmtpAuthenticator(email, password);
        mailDtoQueue = new LinkedList<>();
    }

    public boolean sendLostPasswordEmail(MessageSource messageSource, Integer language, String emailTo, String code, String fullName) {
      //  Locale locale = new Locale(language);
        if(language.equals(Constant.VN)) {
            String subject = messageSource.getMessage(MessageUtil.EMAIL_LOSTPASSWORD_SUBJECT_VI, null, Locale.forLanguageTag("vi"));
            String content = messageSource.getMessage(MessageFormat.format(MessageUtil.EMAIL_LOSTPASSWORD_CONTENT_VI, fullName, code), null, Locale.forLanguageTag("vi"))
                    .replace("{USER_NAME}", fullName)
                    .replace("{CODE}", code);
            return mailDtoQueue.add(new MailDto(emailTo, subject, content));
        } else {
            String subject = messageSource.getMessage(MessageUtil.EMAIL_LOSTPASSWORD_SUBJECTS_LO, null, Locale.forLanguageTag("vi"));
            String content = messageSource.getMessage(MessageFormat.format(MessageUtil.EMAIL_LOSTPASSWORD_CONTENT_LO, fullName, code), null, Locale.forLanguageTag("lo"))
                    .replace("{USER_NAME}", fullName)
                    .replace("{CODE}", code);
            return mailDtoQueue.add(new MailDto(emailTo, subject, content));
        }

    }

    private boolean send(MailDto mailDto) {
        try {
            Properties emailProps = new Properties();
            emailProps.load(PropertiesUtil.class.getResourceAsStream("/email.properties"));
            // Get the default Session object.
            Session session = Session.getDefaultInstance(emailProps, smtpAuthenticator);
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(PropertiesUtil.getEmailProperty("mail.user")));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDto.getEmailTo()));
            // Set Subject: header field
            message.setSubject(mailDto.getSubject(), "UTF-8");

            // Send the actual HTML message, as big as you like
            // message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
            message.setContent(mailDto.getContent(), "text/html; charset=UTF-8");
            // Send message
            Transport.send(message);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);

                MailDto mailDto = mailDtoQueue.poll();
                if (mailDto != null) {
                    String rs = send(mailDto) ? "success" : "fail";
                    log.info("Send mail is " + rs + " (" + mailDto + ")");
                }
            } catch (Exception e) {
                log.error("Lỗi", e);
            }
        }
    }

    public static void main(String args[]) {
//        EmailUtil.getInstance().sendLostPasswordEmail( "vi", "thuannguyenduy1606@gmail.com", "https://google.com.vn", "Luong Tuan Anh");
    }
}
