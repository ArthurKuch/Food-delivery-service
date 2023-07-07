package com.kuch.Fooddelivery.service.impl;

import com.kuch.Fooddelivery.dto.UserDto;
import com.kuch.Fooddelivery.entity.Inventory;
import com.kuch.Fooddelivery.entity.Role;
import com.kuch.Fooddelivery.entity.User;
import com.kuch.Fooddelivery.repository.InventoryRepository;
import com.kuch.Fooddelivery.repository.RoleRepository;
import com.kuch.Fooddelivery.repository.UserRepository;
import com.kuch.Fooddelivery.service.UserService;
import com.kuch.Fooddelivery.service.exception.UserNotFoundException;
import com.kuch.Fooddelivery.utils.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author Artur Kuch
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final InventoryRepository inventoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s).orElseThrow(UserNotFoundException::new);

        log.info("User with email {} is found", user.getEmail());

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public UserDto getUser(int userId) {
        log.info("Finding user by id {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        log.info("User with id {} is found", userId);
        return UserMapper.INSTANCE.asUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Getting all users");
        List<User> users = userRepository.findAll();

        return users.stream().map(UserMapper.INSTANCE::asUserDto).toList();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = UserMapper.INSTANCE.asUser(userDto);
        user = userRepository.save(user);
        log.info("User with id: {} created", user.getUserId());

        Inventory inventory = inventoryRepository.save(new Inventory());

        inventory.setUser(user);

        log.info("Inventory with id: {} created", inventory.getInventoryId());

        user.setInventory(inventory);

        user = userRepository.save(user);

        userDto.setId(user.getUserId());
        return userDto;
    }

    @Override
    public UserDto updateUser(int userId, UserDto userDto) {
        log.info("Update User with id {}", userId);

        User existedUser = userRepository.findById(userId).
                orElseThrow(UserNotFoundException::new);


        updateUserWithNonNullFields(existedUser, userDto);

        userRepository.save(existedUser);

        log.info("User with {} id updated", userId);

        return UserMapper.INSTANCE.asUserDto(existedUser);
    }

    @Override
    public void deleteUser(int userId) {
        log.info("Delete User with id {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
        log.info("User with {} id deleted", userId);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }


    @Override
    public void addRoleToUser(int userId, String roleName) {
        log.info("Adding role {} to user with id: {} ");
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }


    /*
            Method just for implementation.
            Need to fix that piece of code
         */
    private void updateUserWithNonNullFields(User user, UserDto userDto){
        String firstName = userDto.getFirstname();
        String lastName = userDto.getLastname();
        String email = userDto.getEmail();
        String pass = userDto.getPassword();
        String phone = userDto.getPhone();

        if(Objects.nonNull(firstName))
            user.setFirstname(firstName);
        if(Objects.nonNull(lastName))
            user.setLastname(lastName);
        if(Objects.nonNull(email))
            user.setEmail(email);
        if(Objects.nonNull(pass))
            user.setPassword(pass);
        if(Objects.nonNull(phone))
            user.setPhone(phone);

    }
}
