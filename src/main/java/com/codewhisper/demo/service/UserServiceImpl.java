package com.codewhisper.demo.service;

// add missing imports

import com.codewhisper.demo.dto.UserDto;
import com.codewhisper.demo.dto.UserLoginDTO;
import com.codewhisper.demo.dto.UserRegistrationDTO;
import com.codewhisper.demo.entity.Role;
import com.codewhisper.demo.entity.User;
import com.codewhisper.demo.repository.RoleRepository;
import com.codewhisper.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//create UserServiceImpl which implements UserService

/*
    UserServiceImpl class contains methods to saveUser, saveAdmin, findByEmail, findAllUsers
    from UserService interface
 */

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*
    *   Implement saveUser method from UserService
    *   saveUser method takes UserRegistrationDTO as parameter
    */    
    //implement saveUser method from UserService
    @Override
    public void saveUser(userDto user) {
        User localUser = userRepository.findByUsername(user.getUsername());
        if (localUser != null) {
            System.out.println("User is already there");
            return localUser;
        }
        localUser = new User();
        localUser.setUsername(user.getUsername());
        localUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("USER");
        //if role is null, create new Role with name "USER"
        if (role == null) {
            role = new Role();
            role.setName("USER");
            roleRepository.save(role);
        }
        localUser.setRoles(role);
        userRepository.save(localUser);
    }

    /*
    *   Implement saveAdmin method from UserService
    *   saveAdmin method takes UserRegistrationDTO as parameter
    */
    //implement saveAdmin method from UserService
    @Override
    public void saveAdmin(userDto user) {
        User localUser = userRepository.findByUsername(user.getUsername());
        if (localUser != null) {
            System.out.println("User is already there");
            return localUser;
        }
        localUser = new User();
        localUser.setUsername(user.getUsername());
        localUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("ADMIN");
        //if role is null, create new Role with name "ADMIN"
        if (role == null) {
            role = new Role();
            role.setName("ADMIN");
            roleRepository.save(role);
        }
        localUser.setRoles(role);
        userRepository.save(localUser);
    }

    /*
    *   Implement findByEmail method from UserService
    *   findByEmail method takes String as parameter
    */ 
    //implement findByEmail method from UserService
    @Override
    public User findByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    //implement findAllUsers method from UserService
    @Override
    public List<UserDto> findAllUsers() {
        //get all users from userRepository
        List<User> users = userRepository.findAll();
        //convert users to userDtos
        List<UserDto> userDtos = users.stream().map(user -> new UserDto(user.getId(), user.getUsername())).toList();
        return userDtos;
    }

}



