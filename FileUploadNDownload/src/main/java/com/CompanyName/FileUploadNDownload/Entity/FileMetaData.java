package com.CompanyName.FileUploadNDownload.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String filePath;
    private String contentType;
    private long fileLength;
    private LocalDateTime uploadTimestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserInfo user;
}
