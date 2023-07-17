package com.kuch.Fooddelivery.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuch.Fooddelivery.api.UserApi;
import com.kuch.Fooddelivery.controller.assembler.UserAssembler;
import com.kuch.Fooddelivery.controller.model.UserModel;
import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.Role;
import com.kuch.Fooddelivery.service.UserService;
import com.kuch.Fooddelivery.utils.algo.AlgoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

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
    public List<UserModel> getUsers() {
        List<UserDto> userDtos = userService.getAllUsers();

        return userDtos.stream().map(userAssembler::toModel)
                .toList();
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
    public UserModel addRoleToUser(int userId, String roleName) {
        userService.addRoleToUser(userId, roleName);

        UserDto userDto = userService.getUser(userId);

        return userAssembler.toModel(userDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = AlgoUtil.algo();
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String email = decodedJWT.getSubject();
                UserDto user = userService.getUserByEmail(email);
                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            }catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);

            }finally {
                return ResponseEntity.noContent().build();
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
