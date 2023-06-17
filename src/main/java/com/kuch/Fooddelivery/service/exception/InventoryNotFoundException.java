package com.kuch.Fooddelivery.service.exception;

import com.kuch.Fooddelivery.entity.enumeration.ErrorType;

/**
 * @author Artur Kuch
 */
public class InventoryNotFoundException extends ServiceException{

    public InventoryNotFoundException() {
        super("Inventory isn't found!");
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
