package com.kuch.Fooddelivery.service.impl;

import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.User;
import com.kuch.Fooddelivery.repository.UserRepository;
import com.kuch.Fooddelivery.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Artur Kuch
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(int userId) {
        log.info("getUser by id {}", userId);
        User user = userRepository.getUser(userId);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("create user with id {}", userDto.getId());
        User user = mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(int userId, UserDto userDto) {
        log.info("updateUser with id {}", userId);
        User user = mapUserDtoToUser(userDto);

        User oldUser = userRepository.getUser(userId);
        user.setPhone(oldUser.getPhone());

        user = userRepository.updateUser(userId, user);
        return mapUserToUserDto(user);
    }

    @Override
    public void deleteUser(int userId) {
        log.info("deleteUser with id {}", userId);
        userRepository.deleteUser(userId);
    }

    private UserDto mapUserToUserDto(User user){
        return UserDto.builder()
                .id(user.getUserId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto){
        return User.builder()
                .userId(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phone(userDto.getPhone())
                .build();
    }

}
