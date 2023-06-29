package com.kuch.Fooddelivery.entity;

import com.kuch.Fooddelivery.entity.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Artur Kuch
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private int streetNumber;

    @OneToOne
    private Inventory inventory;

    @Column(name = "total_price")
    private double total;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "order_status")
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
