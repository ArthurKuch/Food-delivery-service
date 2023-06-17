package com.kuch.Fooddelivery.service.impl;

import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.Inventory;
import com.kuch.Fooddelivery.entity.User;
import com.kuch.Fooddelivery.repository.FoodRepostiory;
import com.kuch.Fooddelivery.repository.InventoryRepository;
import com.kuch.Fooddelivery.repository.UserRepository;
import com.kuch.Fooddelivery.service.UserService;
import com.kuch.Fooddelivery.service.exception.UserNotFoundException;
import com.kuch.Fooddelivery.utils.mappers.UserMapper;
import fr.xebia.extras.selma.Selma;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Artur Kuch
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final InventoryRepository inventoryRepository;

    private final UserMapper userMapper = Selma.getMapper(UserMapper.class);

    @Override
    public UserDto getUser(int userId) {
        log.info("Finding user by id {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        log.info("User with id {} is found", userId);
        return userMapper.asUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Getting all users");
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = users.stream().map(userMapper::asUserDto).toList();

        return userDtos;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.asUser(userDto);

        user = userRepository.save(user);
        log.info("User with id: {} created", user.getUserId());

        Inventory inventory = inventoryRepository.save(new Inventory());

        inventory.setUser(user);

        log.info("Inventory with id: {} created", inventory.getInventoryId());

        user.setInventory(inventory);

        user = userRepository.save(user);

        userDto.setId(user.getUserId());
        return userDto;
    }

    @Override
    public UserDto updateUser(int userId, UserDto userDto) {
        log.info("Update User with id {}", userId);

        User existedUser = userRepository.findById(userId).
                orElseThrow(UserNotFoundException::new);


        updateUserWithNonNullFields(existedUser, userDto);

        User upUser = userRepository.save(existedUser);

        log.info("User with {} id updated", userId);

        return userMapper.asUserDto(upUser);
    }

    @Override
    public void deleteUser(int userId) {
        log.info("Delete User with id {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
        log.info("User with {} id deleted", userId);
    }


    /*
            Method just for implementation.
            Need to fix that piece of code
         */
    private void updateUserWithNonNullFields(User user, UserDto userDto){
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

    }

}
