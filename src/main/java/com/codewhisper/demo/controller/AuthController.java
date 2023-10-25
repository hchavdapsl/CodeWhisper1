package com.codewhisper.demo.controller;

import com.codewhisper.demo.dto.UserDto;
import com.codewhisper.demo.dto.UserLoginDTO;
import com.codewhisper.demo.entity.User;
import com.codewhisper.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("index")
    public String home(){
        System.out.println("...going ot index...");
    return "index";
    }

    @GetMapping("/login")
    public String login1() {
        System.out.println("logging in ...get");
        return "login";
    }

    //create springboot post mapping for login request which accepts and binds UserLoginDto from thymleaf
    @PostMapping("/loginToSystem")
    public String loginToSystem(@Valid @ModelAttribute("user") UserLoginDTO userLoginDTO, BindingResult result, Model model) {
        //extract username and password from userLoginDTO and send to userService for authentication
        System.out.println("post logging in...." + userLoginDTO);
        User user = userService.authenticate(userLoginDTO.getUserName(), userLoginDTO.getPassword());
        if (user != null) {
            return "redirect:/index";
        }
        return "login";
    }
    

    // handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
