package com.kuch.Fooddelivery.service.exception;

import com.kuch.Fooddelivery.entity.enumeration.ErrorType;

public class FoodNotFoundException extends ServiceException{

    public FoodNotFoundException(){
        super("Food isn't found");
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
