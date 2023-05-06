package com.kuch.Fooddelivery.controller;

import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.dto.group.OnCreate;
import com.kuch.Fooddelivery.dto.group.OnUpdate;
import com.kuch.Fooddelivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Artur Kuch
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable int userId){
        return userService.getUser(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto){
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable int userId,
                              @RequestBody @Validated(OnUpdate.class) UserDto userDto){
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
