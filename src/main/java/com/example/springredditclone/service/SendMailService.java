package com.example.springredditclone.service;

import com.example.springredditclone.config.ApplicationConfig;
import com.example.springredditclone.config.MailConfig;
import com.example.springredditclone.config.SendInBlueConfig;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sendinblue.ApiException;
import sibApi.TransactionalEmailsApi;
import sibModel.CreateSmtpEmail;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendMailService {
    private final ApplicationConfig appConfig;
    private final MailConfig mailConfig;
    @Value("${mail.verification.expiration.time}")
    private Long verificationMailTokenExpirationInMillis;

    @Async
    public void sendMail(String recipient, Long templateId, Map<String, Object> parameters) throws ApiException {
        // Create an instance of the TransactionalEmailsApi
        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();

        // Create an instance of the SendSmtpEmail class
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        sender.setEmail(mailConfig.getSender());
        sender.setName(appConfig.getName());
        sendSmtpEmail.setSender(sender);

        // Set the recipient of the email
        SendSmtpEmailTo to = new SendSmtpEmailTo();
        to.setEmail(recipient);
        List<SendSmtpEmailTo> toList = new ArrayList<>();
        toList.add(to);
        sendSmtpEmail.setTo(toList);

        // Set the template ID and parameters for the email
        sendSmtpEmail.setTemplateId(templateId);
        Properties params = new Properties();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            params.setProperty(entry.getKey(), entry.getValue().toString());
        }
        sendSmtpEmail.setParams(params);

        // Send the email and return the message ID
        CreateSmtpEmail response = apiInstance.sendTransacEmail(sendSmtpEmail);
        response.getMessageId();
    }

    public Long getVerificationMailTokenExpirationInMillis() {
        return verificationMailTokenExpirationInMillis;
    }
}
