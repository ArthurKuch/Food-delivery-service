package com.kuch.Fooddelivery.api.service;

import com.kuch.Fooddelivery.api.util.UserDataTestUtil;
import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.Inventory;
import com.kuch.Fooddelivery.entity.User;
import com.kuch.Fooddelivery.repository.InventoryRepository;
import com.kuch.Fooddelivery.repository.UserRepository;
import com.kuch.Fooddelivery.service.exception.UserNotFoundException;
import com.kuch.Fooddelivery.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author Artur Kuch
 */

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void UserService_CreateUser_ReturnsUserDtoWithSameParameters(){

        User user = UserDataTestUtil.createUser();
        UserDto userDto = UserDataTestUtil.createUserDto();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(inventoryRepository.save(Mockito.any(Inventory.class))).thenReturn(new Inventory());
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("testpass");

        UserDto saved = userService.createUser(userDto);
        System.out.println(saved);
        assertThat(saved).isNotNull();
        assertEqualsUserProperties(user, saved);
    }

    @Test
    public void UserService_GetUserById_ReturnsUserDtoWithSameParameters(){
        User user = UserDataTestUtil.createUser();

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        UserDto existed = userService.getUser(1);
        assertThat(existed).isNotNull();
        assertEqualsUserProperties(user, existed);
    }

    @Test
    public void UserService_GetUserById_ThrowsUserNotFoundException(){
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUser(1));
    }

    @Test
    public void UserService_UpdateUser_ReturnsUserDtoWithSameParameters(){
        User user = UserDataTestUtil.createUser();
        UserDto userDto = UserDataTestUtil.createUserDto();

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDto updated = userService.updateUser(1, userDto);

        assertThat(updated).isNotNull();
        assertEqualsUserProperties(user, updated);
    }

    @Test
    public void UserService_DeleteUserById_ReturnsVoid(){
        User user = UserDataTestUtil.createUser();

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        assertAll(() -> userService.deleteUser(1));
    }

    private void assertEqualsUserProperties(User user, UserDto userDto) {
        assertThat(userDto, allOf(
                hasProperty("id", equalTo(user.getUserId())),
                hasProperty("firstname", equalTo(user.getFirstname())),
                hasProperty("lastname", equalTo(user.getLastname())),
                hasProperty("password", equalTo(user.getPassword())),
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("phone", equalTo(user.getPhone()))
        ));
    }

}
