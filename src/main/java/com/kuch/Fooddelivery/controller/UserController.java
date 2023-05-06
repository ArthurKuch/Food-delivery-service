package com.kuch.Fooddelivery.controller;

import com.kuch.Fooddelivery.api.UserApi;
import com.kuch.Fooddelivery.controller.assembler.UserAssembler;
import com.kuch.Fooddelivery.controller.model.UserModel;
import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Artur Kuch
 */
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final UserAssembler userAssembler;

    @Override
    public UserModel getUser(int userId) {
        UserDto outUserDto = userService.getUser(userId);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public UserModel createUser(UserDto userDto) {
        UserDto outUserDto = userService.createUser(userDto);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public UserModel updateUser(int userId, UserDto userDto) {
        UserDto outUserDto = userService.updateUser(userId, userDto);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
