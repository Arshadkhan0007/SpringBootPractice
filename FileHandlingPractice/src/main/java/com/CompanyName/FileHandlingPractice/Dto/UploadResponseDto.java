package com.CompanyName.FileHandlingPractice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResponseDto {
    private int totalFile;
    private int uploadedFiles;
    private int failures;
    private List<UploadInfoDto> results;
}
