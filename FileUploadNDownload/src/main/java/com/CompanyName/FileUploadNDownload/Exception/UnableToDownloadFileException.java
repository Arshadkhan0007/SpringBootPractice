package com.CompanyName.FileUploadNDownload.Exception;

public class UnableToDownloadFileException extends RuntimeException{
    public UnableToDownloadFileException(String message, Exception ex) {
        super(message);
    }
}
