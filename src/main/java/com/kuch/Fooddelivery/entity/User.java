package com.kuch.Fooddelivery.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Artur Kuch
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phone;



}
