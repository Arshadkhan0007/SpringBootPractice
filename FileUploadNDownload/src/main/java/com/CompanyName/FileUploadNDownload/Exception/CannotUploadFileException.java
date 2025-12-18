package com.CompanyName.FileUploadNDownload.Exception;

public class CannotUploadFileException extends RuntimeException{
    public CannotUploadFileException(String message, Exception ex) {
        super(message);
    }
}
