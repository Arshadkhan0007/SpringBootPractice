package com.example.JavaMailHandlingPractice.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.receiver")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverProperties {

    private String protocol;
    private String host;
    private int port;
    private String userName;
    private String password;
    private String saveDirectory;

}
