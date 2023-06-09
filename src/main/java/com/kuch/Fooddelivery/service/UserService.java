package com.kuch.Fooddelivery.service;

import com.kuch.Fooddelivery.dto.UserDto;

import java.util.List;

/**
 * @author Artur Kuch
 */
public interface UserService {

    UserDto getUser(int userId);

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(int userId, UserDto userDto);

    void deleteUser(int userId);

}
