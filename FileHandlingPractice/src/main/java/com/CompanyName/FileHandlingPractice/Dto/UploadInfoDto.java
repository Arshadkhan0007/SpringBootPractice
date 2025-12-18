package com.CompanyName.FileHandlingPractice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadInfoDto {
    private String fileName;
    private boolean uploaded;
    private String message;
}
