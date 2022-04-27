package com.example.appname.config.aws.credentials;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.example.appname.config.aws.AWSProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSCredentialsConfig {

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider(AWSProperty awsProperty) {
        return awsProperty.isBasicCredentials() ? basicAWSCredentials(awsProperty) : defaultAWSCredentials();
    }

    private AWSCredentialsProvider basicAWSCredentials(AWSProperty awsProperty) {
        BasicAWSCredentials credentials = new BasicAWSCredentials(awsProperty.getAccessKey(), awsProperty.getSecretKey());
        return new AWSStaticCredentialsProvider(credentials);
    }

    private AWSCredentialsProvider defaultAWSCredentials() {
        return new DefaultAWSCredentialsProviderChain();
    }

}
