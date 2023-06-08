package com.kuch.Fooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kuch.Fooddelivery.dto.group.OnCreate;
import com.kuch.Fooddelivery.dto.group.OnUpdate;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * @author Artur Kuch
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class UserDto {

    private int id;

    @NotBlank(message = "First name shouldn't be empty", groups = OnCreate.class)
    private String firstname;

    @NotBlank(message = "Last name shouldn't be empty", groups = OnCreate.class)
    private String lastname;

    @Email(message = "Incorrect email")
    @NotBlank(message = "Email shouldn't be empty", groups = OnCreate.class)
    private String email;

    @NotBlank(message = "Password shouldn't be empty", groups = OnCreate.class)
    private String password;

    @NotBlank(message = "Phone shouldn't be empty", groups = OnCreate.class)
    @Null(message = "Phone should be absent in request", groups = OnUpdate.class)
    private String phone;

}
