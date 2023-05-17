package com.kuch.Fooddelivery.repository;

import com.kuch.Fooddelivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Artur Kuch
 */
@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

}
