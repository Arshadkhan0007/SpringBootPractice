package com.CompanyName.FileUploadNDownload.ObjectMapperUtility;

import com.CompanyName.FileUploadNDownload.Dto.FileMetaDataResponseDto;
import com.CompanyName.FileUploadNDownload.Entity.FileMetaData;
import com.CompanyName.FileUploadNDownload.Repository.FileMetaDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class FileMetaDataObjectMapperUtility {
    private final ObjectMapper mapper;
    private final FileMetaDataRepository fileRepo;

    public FileMetaDataObjectMapperUtility(ObjectMapper mapper, FileMetaDataRepository fileRepo) {
        this.mapper = mapper;
        this.fileRepo = fileRepo;
    }

    public FileMetaDataResponseDto entityToDto(FileMetaData fileMetaData){
        FileMetaDataResponseDto fileMetaDataResponseDto = new FileMetaDataResponseDto();
        fileMetaDataResponseDto = mapper.convertValue(fileMetaData, FileMetaDataResponseDto.class);
        if(fileMetaData.getUser() != null){
            fileMetaDataResponseDto.setUserId(fileMetaData.getUser().getUserId());
        }
        return fileMetaDataResponseDto;
    }
}
