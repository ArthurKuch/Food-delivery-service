package com.kuch.Fooddelivery.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Artur Kuch
 */
@Data
@Builder
public class UserDto {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;

}
