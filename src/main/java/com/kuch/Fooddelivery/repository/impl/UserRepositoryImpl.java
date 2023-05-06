package com.kuch.Fooddelivery.repository.impl;

import com.kuch.Fooddelivery.entity.User;
import com.kuch.Fooddelivery.repository.UserRepository;
import com.kuch.Fooddelivery.service.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Kuch
 */
@Component
public class UserRepositoryImpl implements UserRepository {

    public final List<User> userList = new ArrayList<>();

    @Override
    public User getUser(int userId) {
        return userList.stream()
                .filter(user-> user.getUserId() == userId)
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        userList.add(user);
        return user;
    }

    @Override
    public User updateUser(int userId, User user) {
        boolean isDeleted = userList.removeIf(u -> u.getUserId() == userId);

        if(isDeleted){
            userList.add(user);
        }else{
            throw new UserNotFoundException();
        }

        return user;
    }

    @Override
    public void deleteUser(int userId) {
        userList.removeIf(u -> u.getUserId() == userId);
    }
}
