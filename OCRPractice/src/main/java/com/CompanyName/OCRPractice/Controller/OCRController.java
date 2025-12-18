package com.CompanyName.OCRPractice.Controller;

import com.CompanyName.OCRPractice.Service.OCRService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("OCR/")
public class OCRController {
    private final OCRService service;

    public OCRController(OCRService service) {
        this.service = service;
    }

    @PostMapping("read_image/")
    public ResponseEntity<String> readTextFromImage(@RequestParam("file") MultipartFile file){
        return new ResponseEntity<>(service.extractTextFromImage(file), HttpStatus.OK);
    }
}
