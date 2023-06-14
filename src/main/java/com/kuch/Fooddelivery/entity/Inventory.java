package com.kuch.Fooddelivery.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
@Table(name = "inventory")
public class Inventory {

    @Id
    @Column(name = "inventory_id")
    private int inventoryId;


    @OneToOne
    @MapsId
    @JoinColumn(name = "inventory_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "inventory",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<InventoryItems> inventoryItems = new ArrayList<>();


    @Column(name = "total_price")
    private double total;


}
