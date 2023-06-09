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
@Table(name = "Food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private int foodId;

    @Column(name = "food_name")
    private String name;

    @Column(name = "food_description")
    private String description;

    @Column(name = "price")
    private double price;
}
