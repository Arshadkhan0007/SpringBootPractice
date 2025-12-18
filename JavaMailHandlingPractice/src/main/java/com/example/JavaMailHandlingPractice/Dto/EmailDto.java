package com.example.JavaMailHandlingPractice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private String subject;
    private String from;
    private Date recievedDate;
    private String content;
    private List<String> attachmentFileNames;

}
