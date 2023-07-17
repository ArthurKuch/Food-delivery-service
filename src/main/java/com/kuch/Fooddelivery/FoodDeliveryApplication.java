package com.kuch.Fooddelivery;

import com.kuch.Fooddelivery.dto.FoodDto;
import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.Role;
import com.kuch.Fooddelivery.service.FoodService;
import com.kuch.Fooddelivery.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
//@EnableJpaAuditing
public class FoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApplication.class, args);
	}


	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, FoodService foodService){
		return args -> {
			userService.saveRole(new Role(0, "ROLE_USER"));
			userService.saveRole(new Role(0, "ROLE_ADMIN"));

			UserDto user1 =  userService.createUser(new UserDto(0, "Artur", "Kuch", "test@gmail.com", "pass", "+380686868688", new ArrayList<>(),new ArrayList<>()));
			UserDto user2 =  userService.createUser(new UserDto(0, "Mike", "Wazowski", "wazowski@gmail.com", "mwazowski", "+380686868688", new ArrayList<>(),new ArrayList<>()));

			userService.addRoleToUser(user1.getId(), "ROLE_ADMIN");
			userService.addRoleToUser(user2.getId(), "ROLE_USER");

			foodService.createFood(new FoodDto(0, "test", "desc", 2.0));
		};
	}

}
