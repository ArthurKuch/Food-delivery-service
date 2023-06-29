package com.kuch.Fooddelivery.service.exception;

import com.kuch.Fooddelivery.entity.enumeration.ErrorType;

/**
 * @author Artur Kuch
 */

public class OrderNotFoundException extends ServiceException{

    public OrderNotFoundException() {
        super("Order isn't found");
    }


    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}

