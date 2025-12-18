package com.CompanyName.FileHandlingPractice.Repository;

import com.CompanyName.FileHandlingPractice.Entity.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMetaDataRepository extends JpaRepository<FileMetaData, Integer> {
    public List<FileMetaData> findByUserIdAndFileNameOrderByUploadTimestampDesc(int userId, String fileName);
}