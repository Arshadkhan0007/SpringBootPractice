package com.CompanyName.FileUploadNDownload.Service;

import com.CompanyName.FileUploadNDownload.Configuration.FileStorageProperties;
import com.CompanyName.FileUploadNDownload.Dto.FileMetaDataResponseDto;
import com.CompanyName.FileUploadNDownload.Entity.FileMetaData;
import com.CompanyName.FileUploadNDownload.Exception.*;
import com.CompanyName.FileUploadNDownload.ObjectMapperUtility.FileMetaDataObjectMapperUtility;
import com.CompanyName.FileUploadNDownload.Repository.FileMetaDataRepository;
import com.CompanyName.FileUploadNDownload.Repository.UserInfoRepository;
import com.CompanyName.FileUploadNDownload.Response.SuccessResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FilesService {
    private final FileMetaDataRepository repo;
    private final UserInfoRepository userInfoRepo;
    private final FileMetaDataObjectMapperUtility mapper;
    private final File fileUploadLocation;

    public FilesService(FileMetaDataRepository repo, UserInfoRepository userInfoRepo, FileMetaDataObjectMapperUtility mapper, FileStorageProperties fileStorageProperties) {
        this.repo = repo;
        this.userInfoRepo = userInfoRepo;
        this.mapper = mapper;
        this.fileUploadLocation = new File(fileStorageProperties.getUpload_dir());
    }

    public SuccessResponse<FileMetaDataResponseDto> uploadFile(MultipartFile file, Integer id){
        if(file.isEmpty()){
            throw new EmptyFileException("File that has been provided is empty!, filename: " + file.getOriginalFilename());
        }

//        if(!file.getContentType().equals("application/pdf") && file.getContentType().equals("text/plain")){
//            throw new InvalidFileTypeException("File that has been provided is of type: " + file.getContentType() + " server only accepts .pdf and .txt files");
//        }

        String cleanPath = StringUtils.cleanPath(file.getOriginalFilename());
        if(cleanPath.contains("..")){
            throw new InvalidFileNameException("File name that has been provided is invalid, filename: " + file.getOriginalFilename() + ", make sure file doesn't contain '..'");
        }
        cleanPath  = UUID.randomUUID().toString().substring(0, 6) + "_" + cleanPath;

        try{
            if (!fileUploadLocation.exists() && !fileUploadLocation.mkdirs()) {
                throw new IOException("Upload directory couldn't be created: " + fileUploadLocation);
            }

            File storageLocation = new File(fileUploadLocation, cleanPath).getCanonicalFile();
            if(!storageLocation.toPath().
                    startsWith(fileUploadLocation.getCanonicalFile().toPath())){
                throw new SecurityException("File outside upload directory, provided filePath: " + storageLocation.getAbsolutePath());
            }

            if(file.getContentType().equals("application/pdf")){
                try(BufferedInputStream in = new BufferedInputStream(file.getInputStream());
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(storageLocation))){

                    int readByte;
                    while((readByte = in.read()) != -1){
                        out.write(readByte);
                    }
                    out.flush();
                }
            }
            else if(file.getContentType().equals("text/plain")){
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(storageLocation))){

                    String line;
                    while((line = reader.readLine()) != null){
                        writer.write(line);
                        writer.newLine();
                    }
                    writer.flush();
                }
            }

            FileMetaData fileMetaData = new FileMetaData();
            fileMetaData.setUuid(cleanPath.substring(0, 6));
            fileMetaData.setFileName(cleanPath.substring(7));
            fileMetaData.setFilePath(storageLocation.toString());
            fileMetaData.setContentType(file.getContentType());
            fileMetaData.setFileLength(file.getSize());
            fileMetaData.setUploadTimestamp(LocalDateTime.now());
            fileMetaData.setUser(userInfoRepo.findById(id).
                    orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " doesn't exist!")));
            repo.save(fileMetaData);


            return new SuccessResponse<FileMetaDataResponseDto>(
                    HttpStatus.OK.value(),
                    "1 File has been uploaded successfully",
                    mapper.entityToDto(fileMetaData)
            );

        } catch (IOException ex){
            throw new CannotUploadFileException(file.getOriginalFilename() + " couldn't be uploaded! ", ex);
        }
    }

    public void downloadFile(String fileName, Integer id, HttpServletResponse response){
        String cleanPath = StringUtils.cleanPath(fileName);

        FileMetaData fileMetaData = repo.findTop1ByFileNameAndUser_UserIdOrderByUploadTimestamp(cleanPath, id);

        File filePath = new File(fileMetaData.getFilePath());

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileMetaData.getFileName() + "\"");
        response.setContentType(fileMetaData.getContentType());
        response.setContentLengthLong(fileMetaData.getFileLength());

        try {
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
                 BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {

                int byteRead;
                while ((byteRead = in.read()) != -1) {
                    out.write(byteRead);
                }
                out.flush();

            }
        } catch (IOException ex){
            throw new UnableToDownloadFileException("File couldn't be downloaded", ex);
        }
    }
}
