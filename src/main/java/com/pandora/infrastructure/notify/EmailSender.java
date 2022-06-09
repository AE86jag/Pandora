package com.pandora.infrastructure.notify;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class EmailSender implements ISender {

    private JavaMailSender javaMailSender;

    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean send(IMessage message) {
        if (CollectionUtils.isEmpty(message.getDestinationAddress())) {
            return true;
        }

        String fromAddress = "1475874613@qq.com";
        SimpleMailMessage[] simpleMailMessages = new SimpleMailMessage[message.getDestinationAddress().size()];
        for (int i = 0; i < message.getDestinationAddress().size(); i++) {
            SimpleMailMessage messageContent = new SimpleMailMessage();
            messageContent.setFrom(fromAddress);
            messageContent.setTo(message.getDestinationAddress().get(i));
            messageContent.setSubject(message.getTitle());
            messageContent.setText(message.getContent());
            simpleMailMessages[i] = messageContent;
        }

        javaMailSender.send(simpleMailMessages);
        return true;
    }

    @Override
    public boolean send(List<IMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return true;
        }
        for (IMessage message : messages) {
            send(message);
        }
        return true;
    }
}
