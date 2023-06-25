package com.kuch.Fooddelivery.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuch.Fooddelivery.api.util.FoodDataTestUtil;
import com.kuch.Fooddelivery.api.util.TestConfig;
import com.kuch.Fooddelivery.controller.FoodController;
import com.kuch.Fooddelivery.controller.assembler.FoodAssembler;
import com.kuch.Fooddelivery.controller.model.FoodModel;
import com.kuch.Fooddelivery.dto.FoodDto;
import com.kuch.Fooddelivery.service.FoodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Artur Kuch
 */

@WebMvcTest(controllers = FoodController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Import(TestConfig.class)
public class FoodControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService foodService;

    @MockBean
    private FoodAssembler foodAssembler;

    private final String URI = "/api/v1/foods";

    @Test
    public void FoodController_GetFood_ReturnsExisted() throws Exception{
        FoodDto foodDto = FoodDataTestUtil.createFoodDto();
        FoodModel foodModel = new FoodModel(foodDto);

        when(foodService.getFood(1)).thenReturn(foodDto);
        when(foodAssembler.toModel(foodDto)).thenReturn(foodModel);

        mockMvc.perform(get(URI + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void FoodController_CreateFood_ReturnsStatusCreated() throws Exception{
        FoodDto foodDto = FoodDataTestUtil.createFoodDto();
        FoodModel foodModel = new FoodModel(foodDto);

        when(foodService.createFood(foodDto)).thenReturn(foodDto);
        when(foodAssembler.toModel(foodDto)).thenReturn(foodModel);

        mockMvc.perform(post("/api/v1/foods")
                .content(asJsonString(foodDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void FoodController_UpdateFood_ReturnsUpdatedFoodDto() throws Exception{
        FoodDto foodDto = FoodDataTestUtil.createFoodDto();
        FoodModel foodModel = new FoodModel(foodDto);

        foodDto.setDescription(null);

        when(foodService.updateFood(foodDto.getId(),foodDto)).thenReturn(foodDto);
        when(foodAssembler.toModel(foodDto)).thenReturn(foodModel);

        mockMvc.perform(put(URI + "/{foodId}", foodDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(foodDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(foodDto.getId()));
    }

    private static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}
