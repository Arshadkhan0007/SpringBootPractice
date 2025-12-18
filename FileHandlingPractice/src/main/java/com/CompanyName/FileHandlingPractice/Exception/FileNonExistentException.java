package com.CompanyName.FileHandlingPractice.Exception;

public class FileNonExistentException extends RuntimeException{
    public FileNonExistentException(String message) {
        super(message);
    }
}
