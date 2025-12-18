package com.CompanyName.FileUploadNDownload.Controller;

import com.CompanyName.FileUploadNDownload.Dto.FileMetaDataResponseDto;
import com.CompanyName.FileUploadNDownload.Response.SuccessResponse;
import com.CompanyName.FileUploadNDownload.Service.FilesService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final FilesService service;

    public FilesController(FilesService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<SuccessResponse<FileMetaDataResponseDto>> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id){
        return new ResponseEntity<>(service.uploadFile(file, id), HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<SuccessResponse<String>> downloadFile(@RequestParam("filename") String filename, @RequestParam("id") Integer userId, HttpServletResponse response){
        service.downloadFile(filename, userId, response);
        return new ResponseEntity<>(new SuccessResponse<>(
                HttpStatus.OK.value(),
                "File download has been started",
                filename
                ), HttpStatus.OK);

    }

}
