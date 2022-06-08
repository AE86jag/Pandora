package com.pandora.infrastructure.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailNotify implements INotify {

    @Autowired
    private JavaMailSender sender;

    @Override
    public boolean send(Message message) {
        SimpleMailMessage messageContent = new SimpleMailMessage();

        messageContent.setFrom("ae86@qq.com");
        messageContent.setTo("15717003806@163.com");
        messageContent.setSubject("测试");
        messageContent.setText("这是一个内容，内容很长很长很长很长很长长长长长长长长长长长长");
        sender.send(messageContent);
        return true;
    }

    @Override
    public boolean send(List<Message> messages) {
        return false;
    }
}
