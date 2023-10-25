package com.codewhisper.demo.service;

import com.codewhisper.demo.dto.UserDto;
import com.codewhisper.demo.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    void saveAdmin(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
