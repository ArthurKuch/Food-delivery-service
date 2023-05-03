package com.kuch.Fooddelivery.repository;

import com.kuch.Fooddelivery.entity.User;

/**
 * @author Artur Kuch
 */
public interface UserRepository {

    User getUser(int userId);

    User createUser(User user);

    User updateUser(int userId, User user);

    void deleteUser(int userId);

}
