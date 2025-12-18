package com.CompanyName.FileUploadNDownload.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMetaDataResponseDto {
    private String fileName;
    private String contentType;
    private long fileLength;
    private LocalDateTime timestamp;
    private Integer userId;
}
