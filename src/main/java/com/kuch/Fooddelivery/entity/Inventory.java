package com.kuch.Fooddelivery.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
@Table(name = "Inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private int inventoryId;


    @OneToOne(optional = false, mappedBy = "inventory")
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "food_id")
    private List<Food> usersFoods;

}
