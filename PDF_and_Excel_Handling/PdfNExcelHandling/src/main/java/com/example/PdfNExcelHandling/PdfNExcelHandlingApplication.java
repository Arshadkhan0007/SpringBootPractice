package com.example.PdfNExcelHandling;

import com.example.PdfNExcelHandling.Service.PdfService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootApplication
public class PdfNExcelHandlingApplication implements CommandLineRunner {

    private final PdfService pdfService;

    public PdfNExcelHandlingApplication(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    public static void main(String[] args) {
		SpringApplication.run(PdfNExcelHandlingApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        //READING A TEXT PDF
//        File textPdf = new File("src\\main\\resources\\Files\\Lorem_ipsum.pdf");
//        try (InputStream in = new FileInputStream(textPdf)) {
//            pdfService.extractTextFromRegularPdf(in);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }


        //READING A SCANNED PDF
        File scannedPdf = new File("src/main/resources/Files/ImagePdf.pdf");
        try(InputStream in = new FileInputStream(scannedPdf)){
            pdfService.extractTextFromImagePdf(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
