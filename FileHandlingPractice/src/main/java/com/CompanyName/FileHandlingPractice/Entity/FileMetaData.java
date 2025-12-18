package com.CompanyName.FileHandlingPractice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FILE_META_DATA")
public class FileMetaData {
    @Id
    @SequenceGenerator(
            name = "fileSeq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fileSeq")
    private Integer fileId;
    private String uuid;
    private String fileName;
    private String fileStoragePath;
    private String fileType;
    private long fileSize;
    private LocalDateTime uploadTimestamp;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    private int userId;

    @PrePersist
    protected void assignTimestamp(){
        this.uploadTimestamp = LocalDateTime.now();
    }
}
