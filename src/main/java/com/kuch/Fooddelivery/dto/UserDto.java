package com.kuch.Fooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kuch.Fooddelivery.dto.group.OnCreate;
import com.kuch.Fooddelivery.dto.group.OnUpdate;
import com.kuch.Fooddelivery.entity.Order;
import com.kuch.Fooddelivery.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Artur Kuch
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class UserDto implements Serializable {

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

    private List<Order> order;

    private Collection<Role> roles = new ArrayList<>();
}
