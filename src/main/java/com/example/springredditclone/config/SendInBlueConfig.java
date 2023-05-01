package com.example.springredditclone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibModel.SendSmtpEmailSender;

@org.springframework.context.annotation.Configuration
public class SendInBlueConfig {
    @Value("${sendinblue.apiKey}")
    private String api_key;

    // Set the sender of the email
    public static SendSmtpEmailSender sender;
    static {
        sender = new SendSmtpEmailSender();
        sender.setEmail("aniket2018dutta@gmail.com");
        sender.setName("Aniket Dutta");
    }

    @Bean
    public void sendinblueClient() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(api_key);
    }
}