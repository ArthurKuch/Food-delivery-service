package com.kuch.Fooddelivery.api.controller;

import com.kuch.Fooddelivery.controller.FoodController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

/**
 * @author Artur Kuch
 */

@WebMvcTest(controllers = FoodController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class FoodControllerTests {
}
