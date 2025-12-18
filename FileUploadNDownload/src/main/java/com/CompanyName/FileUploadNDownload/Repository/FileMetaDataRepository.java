package com.CompanyName.FileUploadNDownload.Repository;

import com.CompanyName.FileUploadNDownload.Entity.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetaDataRepository extends JpaRepository<FileMetaData, Integer> {
    public FileMetaData findTop1ByFileNameAndUser_UserIdOrderByUploadTimestamp(String fileName, Integer userId);
}
