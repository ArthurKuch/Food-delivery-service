package com.kuch.Fooddelivery.service;

import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.Role;

import java.util.List;

/**
 * @author Artur Kuch
 */
public interface UserService {

    UserDto getUser(int userId);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(int userId, UserDto userDto);

    void deleteUser(int userId);

    Role saveRole(Role role);

    void addRoleToUser(int userId, String roleName);

}
