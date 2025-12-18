package com.CompanyName.FileUploadNDownload.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    private Integer userId;
    private String userName;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<FileMetaData> fileMetaDataList;
}
