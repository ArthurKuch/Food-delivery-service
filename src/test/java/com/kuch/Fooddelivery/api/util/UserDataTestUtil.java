package com.kuch.Fooddelivery.api.util;

import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.User;
import com.kuch.Fooddelivery.utils.mappers.UserMapper;
import fr.xebia.extras.selma.Selma;

/**
 * @author Artur Kuch
 */
public class UserDataTestUtil {

    private static UserMapper userMapper = Selma.getMapper(UserMapper.class);

    public static User createUser(){
        return User.builder()
                .userId(1)
                .firstname("Testfirstname")
                .lastname("Testlastname")
                .email("test@gmail.com")
                .password("testpass")
                .phone("000000000")
                .build();
    }

    public static UserDto createUserDto(){
        return userMapper.asUserDto(createUser());
    }
}
