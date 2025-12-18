package com.example.JavaMailHandlingPractice.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MimeMailDetails {

    private String to;
    private String subject;
    private String receiverName;
    private MultipartFile attachment;

}
