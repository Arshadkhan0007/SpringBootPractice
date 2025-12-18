package com.CompanyName.FileUploadNDownload;

import com.CompanyName.FileUploadNDownload.Entity.UserInfo;
import com.CompanyName.FileUploadNDownload.Repository.UserInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FileUploadNDownloadApplication implements CommandLineRunner {

    private final UserInfoRepository userInfoRepo;

    public FileUploadNDownloadApplication(UserInfoRepository userInfoRepo) {
        this.userInfoRepo = userInfoRepo;
    }

    public static void main(String[] args) {
		SpringApplication.run(FileUploadNDownloadApplication.class, args);
	}

    public void run(String... args){
        userInfoRepo.saveAll(List.of(
                new UserInfo(101, "Alex", null),
                new UserInfo(102, "Benny", null),
                new UserInfo(103, "Charlie", null),
                new UserInfo(104, "Danny", null),
                new UserInfo(105, "Earl", null)
        ));
        System.out.println("5 users have been added successfully!");
    }

}
