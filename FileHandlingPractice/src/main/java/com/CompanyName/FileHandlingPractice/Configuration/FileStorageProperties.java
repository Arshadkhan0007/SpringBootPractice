package com.CompanyName.FileHandlingPractice.Configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "files")
@Data
public class FileStorageProperties {
    private String upload_dir;
}
