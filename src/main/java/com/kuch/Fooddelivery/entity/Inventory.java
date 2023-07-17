package com.kuch.Fooddelivery.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Artur Kuch
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inventories")
@ToString(exclude = {"user"})
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private int inventoryId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany
    private Set<InventoryFood> usersFood = new HashSet<>();


    @Column(name = "total_price")
    private double total;


}
