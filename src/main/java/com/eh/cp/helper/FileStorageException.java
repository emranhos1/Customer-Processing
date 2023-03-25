package com.eh.cp.helper;
/**
* @author MD. EMRAN HOSSAIN
* @since 24 MAR, 2023
* @version 1.1
*/
@SuppressWarnings("serial")
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}