package com.codewhisper.demo.service;

import com.codewhisper.demo.dto.UserDto;
import com.codewhisper.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
    void deleteUser(Long id);
    User updateUser(Long id, User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();

    User findByEmail(String email);

    User authenticate(String userName, String password);

    User findByUserName(String userName);

    void save(User userForm);

}
