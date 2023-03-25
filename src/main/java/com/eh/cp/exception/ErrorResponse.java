package com.eh.cp.exception;

import com.eh.cp.payload.ApiResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
* @author MD. EMRAN HOSSAIN
* @since 24 MAR, 2023
* @version 1.1
*/
@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse extends ApiResponse{

    private String error;

    public ErrorResponse(Boolean success, String messageEn, String messageBn, String error) {
        super(success, messageEn, messageBn);
        this.error = error;
    }
    
    public ErrorResponse(String messageEn, String messageBn, String error) {
        super(messageEn, messageBn);
        this.error = error;
    }
    
    public ErrorResponse(Boolean success, String messageEn, String messageBn, String error, Object data) {
        super(success, messageEn, messageBn, data);
        this.error = error;
    }
    
    public ErrorResponse(Boolean success, String messageEn, String messageBn, Object data) {
        super(success, messageEn, messageBn, data);
    }
}
