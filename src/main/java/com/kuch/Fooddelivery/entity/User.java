package com.kuch.Fooddelivery.entity;

import com.kuch.Fooddelivery.entity.enumeration.Role;
import lombok.*;

/**
 * @author Artur Kuch
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    private int userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;
//    private Role role;

}
