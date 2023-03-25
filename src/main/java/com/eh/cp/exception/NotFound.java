package com.eh.cp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
* @author MD. EMRAN HOSSAIN
* @since 24 MAR, 2023
* @version 1.1
*/
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public NotFound(String message) {
        super(message);
    }
}
