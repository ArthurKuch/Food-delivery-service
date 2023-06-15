package com.kuch.Fooddelivery.controller.assembler;

import com.kuch.Fooddelivery.controller.FoodController;
import com.kuch.Fooddelivery.controller.model.FoodModel;
import com.kuch.Fooddelivery.dto.FoodDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FoodAssembler extends RepresentationModelAssemblerSupport<FoodDto, FoodModel> {

    public static final String GET_REL = "get_food";
    public static final String CREATE_REL = "create_food";
    public static final String UPDATE_REL = "update_food";
    public static final String DELETE_REL = "delete_food";

    public FoodAssembler(){
        super(FoodController.class, FoodModel.class);
    }


    @Override
    public FoodModel toModel(FoodDto entity) {
        FoodModel foodModel = new FoodModel(entity);

        Link get = linkTo(methodOn(FoodController.class).getFood(entity.getId())).withRel(GET_REL);
        Link create = linkTo(methodOn(FoodController.class).createFood(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(FoodController.class).updateFood(entity.getId(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(FoodController.class).deleteFood(entity.getId())).withRel(DELETE_REL);

        foodModel.add(get, create, update, delete);

        return foodModel;
    }
}
