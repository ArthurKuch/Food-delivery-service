package com.kuch.Fooddelivery.service.exception;

import com.kuch.Fooddelivery.entity.enumeration.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ServiceException extends RuntimeException{

    private ErrorType errorType;

    public ServiceException(String message) {
        super(message);
    }

    public ErrorType getErrorType(){
        return ErrorType.FATAL_ERROR_TYPE;
    }

}
