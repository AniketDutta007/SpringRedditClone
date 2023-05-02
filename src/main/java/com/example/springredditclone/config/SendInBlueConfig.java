package com.example.springredditclone.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibModel.SendSmtpEmailSender;

@org.springframework.context.annotation.Configuration
@RequiredArgsConstructor
public class SendInBlueConfig {
    @Value("${sendinblue.apiKey}")
    private String api_key;

    @Bean
    public void sendinblueClient() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(api_key);
    }
}