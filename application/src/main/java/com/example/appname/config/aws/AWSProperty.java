package com.example.appname.config.aws;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "aws")
public class AWSProperty {

    private String endpoint;
    private String region;
    private String accessKey;
    private String secretKey;
    private boolean basicCredentials;

}
