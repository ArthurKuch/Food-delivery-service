package com.kuch.Fooddelivery.service;

import com.kuch.Fooddelivery.dto.UserDto;

/**
 * @author Artur Kuch
 */
public interface UserService {

    UserDto getUser(int userId);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(int userId, UserDto userDto);

    void deleteUser(int userId);

}
