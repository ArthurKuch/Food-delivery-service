package com.kuch.Fooddelivery.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Artur Kuch
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "inventory_items")
public class InventoryItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name="inventory_id", nullable=false)
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(name = "quantity")
    private int quantity;






}
