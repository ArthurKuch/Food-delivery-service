package com.kuch.Fooddelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private int inventoryId;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany
    private Set<Food> usersFood = new HashSet<>();


    @Column(name = "total_price")
    private double total;

//    public int getInventoryId() {
//        return inventoryId;
//    }
//
//    public void setInventoryId(int inventoryId) {
//        this.inventoryId = inventoryId;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Set<Food> getUsersFood() {
//        return usersFood;
//    }
//
//    public void setUsersFood(Set<Food> usersFood) {
//        this.usersFood = usersFood;
//    }
//
//    public double getTotal() {
//        return total;
//    }
//
//    public void setTotal(double total) {
//        this.total = total;
//    }
}
