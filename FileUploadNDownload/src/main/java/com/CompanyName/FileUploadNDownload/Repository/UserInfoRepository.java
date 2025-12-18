package com.CompanyName.FileUploadNDownload.Repository;

import com.CompanyName.FileUploadNDownload.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
}
