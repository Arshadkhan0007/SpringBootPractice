package com.CompanyName.FileHandlingPractice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMetaDataDto {
    private String fileName;
    private String fileType;
    private long fileSize;
//    private UserDto userDto;
}
