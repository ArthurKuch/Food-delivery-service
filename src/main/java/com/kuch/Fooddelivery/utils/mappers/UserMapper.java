package com.kuch.Fooddelivery.utils.mappers;

import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.User;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.Mapper;

/**
 * @author Artur Kuch
 */

@Mapper( withCustomFields = {
        @Field({"userId","id"})
})
public interface UserMapper {


    UserDto asUserDto(User user);

    User asUser(UserDto userDto);

}
