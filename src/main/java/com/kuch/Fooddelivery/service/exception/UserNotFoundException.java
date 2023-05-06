package com.kuch.Fooddelivery.service.exception;


import com.kuch.Fooddelivery.entity.enumeration.ErrorType;

public class UserNotFoundException extends ServiceException{


    public UserNotFoundException(){
        super("User isn't found");
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
