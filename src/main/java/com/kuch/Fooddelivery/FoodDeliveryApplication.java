package com.kuch.Fooddelivery;

import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.Role;
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
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(0, "ROLE_USER"));
			userService.saveRole(new Role(0, "ROLE_ADMIN"));

			userService.createUser(new UserDto(0, "Artur", "Artur", "test@gmail.com", "123", "111", new ArrayList<>(),new ArrayList<>()));

			userService.addRoleToUser(1, "ROLE_ADMIN");
		};
	}

}
