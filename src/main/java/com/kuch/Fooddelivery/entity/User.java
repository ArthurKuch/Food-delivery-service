package com.kuch.Fooddelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Artur Kuch
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventoryId")
    private Inventory inventory;


//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Order> order;

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
