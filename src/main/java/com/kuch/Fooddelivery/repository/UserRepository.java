package com.kuch.Fooddelivery.repository;

import com.kuch.Fooddelivery.entity.User;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "inventory_items")
@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

}
