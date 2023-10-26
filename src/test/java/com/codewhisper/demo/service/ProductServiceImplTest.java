// Create Unit test for ProeductServiceImpl

package com.codewhisper.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.codewhisper.demo.dto.UserDto;
import com.codewhisper.demo.entity.Role;
import com.codewhisper.demo.entity.User;
import com.codewhisper.demo.repository.RoleRepository;
import com.codewhisper.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSaveUser_NewUser() {
        UserDto userDto = new UserDto("John", "Doe", "password", "mymail@test.com");
        Role role = new Role("USER");

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(null);
        when(roleRepository.findByName("USER")).thenReturn(role);

        userService.saveUser(userDto);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testSaveUser_UserExists() {
        UserDto userDto = new UserDto("John", "Doe", "password", "mymail@test.com");
        User existingUser = new User();
        existingUser.setEmail(userDto.getEmail());

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(existingUser);

        userService.saveUser(userDto);

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testSaveUser_RoleDoesNotExist() {
        UserDto userDto = new UserDto("newuser@example.com", "New", "User", "password");

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(null);
        when(roleRepository.findByName("USER")).thenReturn(null);

        userService.saveUser(userDto);

        verify(roleRepository, times(1)).save(any(Role.class));
    }
}