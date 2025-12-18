package com.example.JavaMailHandlingPractice.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleEmailDetails {

    private String to;
    private String subject;
    private String body;

}
