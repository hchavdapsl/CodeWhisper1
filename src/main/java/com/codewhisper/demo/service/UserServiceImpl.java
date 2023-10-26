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
    public void saveUser(UserDto user) {
        User localUser = userRepository.findByEmail(user.getEmail());
        if (localUser != null) {
            System.out.println("User is already there");
            return;
        }
        localUser = new User();
        localUser.setName(user.getFirstName() + " " + user.getLastName());
        localUser.setPassword(passwordEncoder.encode(user.getPassword()));
        localUser.setEmail(user.getEmail());
        Role role = roleRepository.findByName("USER");
        //if role is null, create new Role with name "USER"
        if (role == null) {
            role = new Role();
            role.setName("USER");
            roleRepository.save(role);
        }
        localUser.setRoles(Arrays.asList(role));
        userRepository.save(localUser);
    }

    /*
    *   Implement saveAdmin method from UserService
    *   saveAdmin method takes UserRegistrationDTO as parameter
    */
    //implement saveAdmin method from UserService
    @Override
    public void saveAdmin(UserDto user) {
        User localUser = userRepository.findByEmail(user.getEmail());
        if (localUser != null) {
            System.out.println("User is already there");
            return;
        }
        localUser = new User();
        localUser.setName(user.getFirstName() + " " + user.getLastName());
        localUser.setPassword(passwordEncoder.encode(user.getPassword()));
        localUser.setEmail(user.getEmail());
        Role role = roleRepository.findByName("ADMIN");
        //if role is null, create new Role with name "ADMIN"
        if (role == null) {
            role = new Role();
            role.setName("ADMIN");
            roleRepository.save(role);
        }
        localUser.setRoles(Arrays.asList(role));
        userRepository.save(localUser);
    }

    /*
    *   Implement findByEmail method from UserService
    *   findByEmail method takes String as parameter
    */ 
    //implement findByEmail method from UserService
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //implement findAllUsers method from UserService
    @Override
    public List<UserDto> findAllUsers() {
        //get all users from userRepository
        List<User> users = userRepository.findAll();
        //convert users to userDtos
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }
    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}



