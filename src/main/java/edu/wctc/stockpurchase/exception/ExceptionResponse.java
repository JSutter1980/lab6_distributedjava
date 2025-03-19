package edu.wctc.stockpurchase.exception;

import lombok.Data;

@Data
public class ExceptionResponse {
    private int httpStatus;
    private String message;
    private String path;

    public ExceptionResponse(int httpStatus, String message, String path) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.path = path;
    }
}
