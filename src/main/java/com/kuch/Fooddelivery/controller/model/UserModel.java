package com.kuch.Fooddelivery.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.kuch.Fooddelivery.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserModel extends RepresentationModel<UserModel> {

    @JsonUnwrapped
    private UserDto userDto;

}
