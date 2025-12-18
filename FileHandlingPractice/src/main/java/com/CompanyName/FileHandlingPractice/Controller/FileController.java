package com.CompanyName.FileHandlingPractice.Controller;

import com.CompanyName.FileHandlingPractice.Configuration.FileStorageProperties;
import com.CompanyName.FileHandlingPractice.Dto.UploadResponseDto;
import com.CompanyName.FileHandlingPractice.Exception.EmptyFileException;
import com.CompanyName.FileHandlingPractice.Exception.FileNonExistentException;
import com.CompanyName.FileHandlingPractice.Exception.InvalidFileNameException;
import com.CompanyName.FileHandlingPractice.Response.SuccessResponse;
import com.CompanyName.FileHandlingPractice.Service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService service;
    private final Path fileStorageLocation;

    public FileController(FileService service, FileStorageProperties fileStorageLocation) {
        this.service = service;
        this.fileStorageLocation = Paths.get(fileStorageLocation.getUpload_dir());
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<SuccessResponse<UploadResponseDto>> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int userId){
        if(file.isEmpty()){
            throw new EmptyFileException("The file that has been provided is empty!");
        }

        String cleanPath = StringUtils.cleanPath(file.getOriginalFilename());
        if(cleanPath.contains("..")){
            throw new InvalidFileNameException("The file name that has been provided is invalid, filename: " + cleanPath);
        }

        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "File uploaded successfully",
                service.uploadFile(file, userId)
        ), HttpStatus.OK);
    }

    @PostMapping("/upload_all_files")
    public ResponseEntity<SuccessResponse<UploadResponseDto>> uploadAllFiles(@RequestParam("files") List<MultipartFile> files, @RequestBody List<Integer> userIdList){
        if(files.isEmpty()){
            throw new EmptyFileException("An empty file list has been provided");
        }

        UploadResponseDto uploadResponseDto = service.uploadAllFiles(files, userIdList);

        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                uploadResponseDto.getUploadedFiles() + " File(s) have been uploaded successfully!",
                uploadResponseDto
        ), HttpStatus.OK);
    }

    @GetMapping("/download/{filename}")
    public void downloadFile(@PathVariable String filename, HttpServletResponse response){

        String cleanPath = StringUtils.cleanPath(filename);
        if(cleanPath.contains("..")){
            throw new InvalidFileNameException("The file name that has been provided is invalid, filename: " + filename);
        }

        Path filePath = fileStorageLocation.resolve(cleanPath).toAbsolutePath().normalize();

        if(!Files.exists(filePath)){
            throw new FileNonExistentException(filename + " does not exist in the file system");
        }

        service.loadFile(filename, filePath, response);
    }

    @GetMapping("/download_uuid/{userId}/{filename}")
    public void downloadUudiFile(@PathVariable Integer userId ,@PathVariable String filename, HttpServletResponse reponse){
        String cleanPath = StringUtils.cleanPath(filename);
        if(cleanPath.contains("..")){
            throw new InvalidFileNameException("The file name that has been provided is invalid!, filename: " + filename);
        }

        service.loadFileById(filename, userId, reponse);

    }
}
