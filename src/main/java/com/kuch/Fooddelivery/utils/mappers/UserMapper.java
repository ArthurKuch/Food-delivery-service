package com.kuch.Fooddelivery.utils.mappers;

import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author Artur Kuch
 */

@Mapper(nullValueMappingStrategy =  NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userId", target = "id")
    UserDto asUserDto(User user);

    @Mapping(source = "id", target = "userId")
    User asUser(UserDto userDto);

}
