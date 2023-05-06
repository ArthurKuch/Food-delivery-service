package com.kuch.Fooddelivery.entity;

import com.kuch.Fooddelivery.entity.enumeration.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {

    private String message;
    private ErrorType errorType;
    private LocalDateTime localDateTime;

}
