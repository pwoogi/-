package com.dashboard.projectboard.exception;

//Todo: implement

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

    public BoardException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.message = null;

    }

    @Override
    public String getMessage() {
        if (message == null){
            return errorCode.getMessage();
        }else {
            return String.format("%s. %s", errorCode.getMessage(), message);
        }
    }
}
