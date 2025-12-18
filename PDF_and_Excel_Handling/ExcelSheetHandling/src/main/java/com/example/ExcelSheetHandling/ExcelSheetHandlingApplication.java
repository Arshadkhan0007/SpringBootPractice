package com.example.ExcelSheetHandling;

import com.example.ExcelSheetHandling.Service.ExcelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class ExcelSheetHandlingApplication implements CommandLineRunner {

    private final ExcelService excelService;

    public ExcelSheetHandlingApplication(ExcelService excelService) {
        this.excelService = excelService;
    }

    public static void main(String[] args) {
		SpringApplication.run(ExcelSheetHandlingApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        File excelFile = new File("src/main/resources/Excel Files/DummyExcel.xlsx");
        try(InputStream in = new FileInputStream(excelFile)){
            excelService.readExcel(in);
        }catch (IOException e){
            System.out.println("Couldn't read excel sheet");
        }
    }
}
