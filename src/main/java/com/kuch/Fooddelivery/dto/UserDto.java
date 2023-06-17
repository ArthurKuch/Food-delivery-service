package com.kuch.Fooddelivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kuch.Fooddelivery.dto.group.OnCreate;
import com.kuch.Fooddelivery.dto.group.OnUpdate;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.io.Serializable;

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
