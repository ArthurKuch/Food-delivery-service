package com.kuch.Fooddelivery.service.impl;

import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.User;
import com.kuch.Fooddelivery.repository.UserRepository;
import com.kuch.Fooddelivery.service.UserService;
import com.kuch.Fooddelivery.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        log.info("Finding user by id {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        log.info("User with id {} is found", userId);

        return mapUserToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("create user with id {}", userDto.getId());
        User user = mapUserDtoToUser(userDto);
        user = userRepository.save(user);

        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(int userId, UserDto userDto) {
        log.info("updateUser with id {}", userId);

        User existedUser = userRepository.findById(userId).
                orElseThrow(UserNotFoundException::new);


        existedUser = updateUserWithNonNullFields(existedUser, userDto);

        User upUser = userRepository.save(existedUser);

        log.info("User with {} id updated", userId);

        return mapUserToUserDto(upUser);
    }

    @Override
    public void deleteUser(int userId) {
        log.info("deleteUser with id {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
        log.info("User with {} id deleted", userId);
    }

    /*
      Method just for implementation.
      Will be removed after modelMapper impl.
   */
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

    /*
        Method just for implementation.
        Will be removed after modelMapper impl.
    */
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


    /*
        Method just for implementation.
        Will be removed after modelMapper impl.
     */
    private User updateUserWithNonNullFields(User user, UserDto userDto){
        String firstName = userDto.getFirstname();
        String lastName = userDto.getLastname();
        String email = userDto.getEmail();
        String pass = userDto.getPassword();
        String phone = userDto.getPhone();

        if(Objects.nonNull(firstName))
            user.setFirstname(firstName);
        if(Objects.nonNull(lastName))
            user.setLastname(lastName);
        if(Objects.nonNull(email))
            user.setEmail(email);
        if(Objects.nonNull(pass))
            user.setPassword(pass);
        if(Objects.nonNull(phone))
            user.setPhone(phone);

        return user;
    }

}
