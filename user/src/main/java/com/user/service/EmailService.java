package com.user.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Service class for handling email sending operations.
 * This class uses {@link JavaMailSender} to send emails with specified content.
 */
@Service
@Slf4j
public class EmailService {
  /**
   * The {@link JavaMailSender} used to send emails.
   */
  @Autowired
  private JavaMailSender javaMailSender;

  /**
   * Sends an email to a list of recipients with the specified content.
   *
   * @param from  the email address of the sender
   * @param to    a list of email addresses of the recipients
   * @param text  the content of the email
   */
  public void sendMail(final String from, final List<String> to, final String text) {
    log.info("Trying to send mail from : {}, to : {} ", from, to);
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);
      helper.setFrom(from);
      helper.setTo(to.toArray(new String[0]));
      helper.setText(text);
      javaMailSender.send(message);
    } catch (Exception e) {
      log.error("Error sending email from: {} to: {}", from, to, e);
    }
    log.info("Successfully sended email from : {} to: {}", from, to);
  }
}

