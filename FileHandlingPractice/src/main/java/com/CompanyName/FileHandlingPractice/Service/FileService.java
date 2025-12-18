package com.CompanyName.FileHandlingPractice.Service;

import com.CompanyName.FileHandlingPractice.Configuration.FileStorageProperties;
import com.CompanyName.FileHandlingPractice.Dto.UploadInfoDto;
import com.CompanyName.FileHandlingPractice.Dto.UploadResponseDto;
import com.CompanyName.FileHandlingPractice.Entity.FileMetaData;
import com.CompanyName.FileHandlingPractice.Exception.*;
import com.CompanyName.FileHandlingPractice.Repository.FileMetaDataRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private final Path fileStorageLocation;
    private final FileMetaDataRepository repo;

    public FileService(FileStorageProperties fileStorageProperties, FileMetaDataRepository repo) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUpload_dir());
        this.repo = repo;

        Path path = fileStorageLocation.toAbsolutePath().normalize();
        try {
            Files.createDirectories(path);
        } catch (IOException ex){
            throw new UnableToCreateRootDirectoryException("Root directory couldn't be creates, reason: " + ex.getMessage());
        }

    }

    //Uploading a single file
    public UploadResponseDto uploadFile(MultipartFile file, int userId){
        FileMetaData fileMetaData = new FileMetaData();
        try(InputStream in = file.getInputStream()){

            //Assigning a new unique UUID to the file name
            String filename = Paths.get(file.getOriginalFilename()).getFileName().toString();
            fileMetaData.setFileName(filename);
            UUID uuid = UUID.randomUUID();
            fileMetaData.setUuid(uuid.toString());
            String uniqueFileName = uuid + "_" + file.getOriginalFilename();
            Path finalPath = fileStorageLocation
                    .resolve(uniqueFileName)
                    .toAbsolutePath()
                    .normalize();

            //Creating parent directories to avoid exceptions and uploading the file using streams
            Files.createDirectories(finalPath.getParent());
            Files.copy(in, finalPath, StandardCopyOption.REPLACE_EXISTING);

            //Inserting metadata into the database
            fileMetaData.setFileStoragePath(uniqueFileName);

            fileMetaData.setFileType(
                    (Files.probeContentType(finalPath) != null)?
                            Files.probeContentType(finalPath) : "application/octet-stream");

            fileMetaData.setFileSize(Files.size(finalPath));
            fileMetaData.setUserId(userId);

            repo.save(fileMetaData);

            return new UploadResponseDto(
                    1,
                    1,
                    0,
                    new ArrayList<>(List.of(new UploadInfoDto(
                            file.getOriginalFilename(), true, "File uploaded successfully"
                    ))));

        } catch (IOException ex){
            throw new UnableToUploadFileException("File couldn't be uploaded, reason: " + ex.getMessage());
        }
    }

    //Uploading multiple files
    public UploadResponseDto uploadAllFiles(List<MultipartFile> files, List<Integer> userIdList){
        int successfulUploads = 0;
        int userIdIndx = 0;
        List<UploadInfoDto> uploadInfoDtoList = new ArrayList<>();

        for(MultipartFile file : files){
            FileMetaData fileMetaData = new FileMetaData();
            try(InputStream in = file.getInputStream()) {
                if (file.isEmpty()) {
                    throw new EmptyFileException("The file that has been provided is empty!");
                }

                String cleanPath = StringUtils.cleanPath(file.getOriginalFilename());
                if(cleanPath.contains("..")){
                    throw new InvalidFileNameException("The file name that has be provided is invalid, filename: " + cleanPath);
                }

                String fileName = Paths.get(cleanPath).getFileName().toString();
                fileMetaData.setFileName(fileName);
                UUID uuid = UUID.randomUUID();
                fileMetaData.setUuid(uuid.toString());
                String uniqueFileName = uuid + "_" + file.getOriginalFilename();

                Path finalPath = fileStorageLocation
                        .resolve(uniqueFileName)
                        .toAbsolutePath()
                        .normalize();

                Files.createDirectories(finalPath.getParent());

                Files.copy(in, finalPath, StandardCopyOption.REPLACE_EXISTING);

                fileMetaData.setFileName(fileName);
                fileMetaData.setFileStoragePath(uniqueFileName);

                fileMetaData.setFileType(
                        (Files.probeContentType(finalPath) != null)?
                                Files.probeContentType(finalPath) : "application/octet-streams");

                fileMetaData.setFileSize(Files.size(finalPath));
                fileMetaData.setUserId(userIdList.get(userIdIndx));
                userIdIndx++;

                repo.save(fileMetaData);

                uploadInfoDtoList.add(new UploadInfoDto(
                        file.getOriginalFilename(),
                        true,
                        "File has been uploaded successfully"));
                successfulUploads++;

            } catch (Exception ex){
                uploadInfoDtoList.add(new UploadInfoDto(
                        file.getOriginalFilename(),
                        false,
                        "File couldn't be uploaded, reason: " + ex.getMessage()
                ));
            }
        }

        return new UploadResponseDto(
                files.size(),
                successfulUploads,
                files.size() - successfulUploads,
                uploadInfoDtoList
        );
    }

    //Downloading a file
    public void loadFile(String filename, Path filePath, HttpServletResponse response){
        try(InputStream in = Files.newInputStream(filePath);
            OutputStream out = response.getOutputStream()){

            try {
                response.setContentType(Files.probeContentType(filePath));
            } catch (IOException ex){
                response.setContentType("application/octet-stream");
            }

            response.setHeader("Content-Disposition", "attachment; filename=\"" + filePath.getFileName() + "\"");

            byte[] buffer = new byte[8192];
            int byteRead;
            while((byteRead = in.read(buffer)) != -1){
                out.write(buffer, 0, byteRead);
            }
            out.flush();

        } catch(IOException ex){
            throw new UnableToCreateStreamsException("Unable to create stream(s) or something went wrong while reading or writing, reason: " + ex.getMessage());
        }
    }

    //Downloading a file through userId
    public void loadFileById(String filename, Integer userId, HttpServletResponse response){
        List<FileMetaData> fileMetaDataList = repo.findByUserIdAndFileNameOrderByUploadTimestampDesc(userId, filename);

        if(fileMetaDataList == null){
            throw new FileNonExistentException(filename + " does not exist in metadata");
        }

        FileMetaData fileMetaData = fileMetaDataList.get(0);

        Path filePath = fileStorageLocation.resolve(fileMetaData.getFileStoragePath()).toAbsolutePath().normalize();

        if(!Files.exists(filePath)){
            throw new FileNonExistentException(filename + " does not exist in the file system");
        }

        try(InputStream in = Files.newInputStream(filePath);
            OutputStream out = response.getOutputStream()){

            byte[] buffer = new byte[8192];
            int byteRead;
            while((byteRead = in.read(buffer)) != -1){
                out.write(buffer, 0, byteRead);
            }
            out.flush();

        } catch (IOException ex){
            throw new UnableToUploadFileException("Unable to create stream(s) or something went wrong while reading or writing, reason: \"" + ex.getMessage());
        }
    }
}
