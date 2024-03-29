package com.kuch.Fooddelivery.api;

import com.kuch.Fooddelivery.controller.model.UserModel;
import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.dto.group.OnCreate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(tags = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId", paramType = "path", required = true, value = "User id")
            })
    @ApiOperation("Get user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    UserModel getUser(@PathVariable int userId);

    @ApiOperation("Get all users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<UserModel> getUsers();

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserModel createUser(@Validated(OnCreate.class) @RequestBody UserDto userDto);

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId", paramType = "path", required = true, value = "User id")
            })
    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}")
    UserModel updateUser(@PathVariable int userId, @RequestBody UserDto userDto);

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId", paramType = "path", required = true, value = "User id")
            })
    @ApiOperation("Add role to user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{userId}")
    UserModel addRoleToUser(@PathVariable int userId, @RequestBody String roleName);

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userId", paramType = "path", required = true, value = "User id")
            })
    @ApiOperation("Delete user")
    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable int userId);

    @ApiOperation("Refresh token")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/refreshToken")
    ResponseEntity<Void> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
