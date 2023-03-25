package com.eh.cp.payload;

import lombok.Getter;
import lombok.Setter;
/**
 * @author MD. EMRAN HOSSAIN
 * @since 24 MAR, 2023
 * @version 1.1
*/
@Getter @Setter
public class ApiResponse {

    private Boolean success;
    private String messageEn;
    private String messageBn;
    private Object data;
    private Object obj;
    private Long count;

    public ApiResponse(Boolean success, String messageEn, String messageBn) {
        this.success = success;
        this.messageEn = messageEn;
        this.messageBn = messageBn;
    }

    public ApiResponse(Boolean success, String messageEn, String messageBn, Object data) {
        this.success = success;
        this.messageEn = messageEn;
        this.messageBn = messageBn;
        this.data = data;
    }

    public ApiResponse(Boolean success, String messageEn, String messageBn, Object data, Long count) {
        this.success = success;
        this.messageEn = messageEn;
        this.messageBn = messageBn;
        this.data = data;
        this.count = count;
    }

    public ApiResponse(String messageEn, String messageBn) {
        this.messageEn = messageEn;
        this.messageBn = messageBn;
    }

    public ApiResponse(){}
}
