package com.posgrado.ecommerce.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

  private JavaMailSender javaMailSender;

  @Override
  public void sendText(String to, String subject, String body) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(to);
    mailMessage.setSubject(subject);
    mailMessage.setText(body);
    javaMailSender.send(mailMessage);
  }

  @Override
  public void sendTemplate(String to, String subject, String body) {
    MimeMessage mailMessage = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(body, true);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
    javaMailSender.send(mailMessage);
  }
}
