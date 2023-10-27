package com.codewhisper.demo;

import com.codewhisper.demo.dto.UserDto;
import com.codewhisper.demo.entity.Product;
import com.codewhisper.demo.entity.Role;
import com.codewhisper.demo.repository.ProductRepository;
import com.codewhisper.demo.repository.RoleRepository;
import com.codewhisper.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodeWhisperDemoApplication implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {

        SpringApplication.run(CodeWhisperDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //load role data into database with role names as ADMIN and USER
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));

        //load user data into database using name, username, password and email, use indian names
        userService.saveAdmin(new UserDto("admin", "admin", "admin", "test@test.com"));
        userService.saveUser(new UserDto("Sandeep", "sandeep", "sandeep", "sandeep@test.com"));
        userService.saveUser(new UserDto("Raj", "raj", "raj", "raj@test.com"));

        productRepository.save(new Product("Diamond Queen", "Diamond ring", 2000, "ring_diamond.jpg"));
        productRepository.save(new Product("Gold Queen", "Gold ring", 1000, "ring_gold.jpg"));
        productRepository.save(new Product("Silver Queen", "Silver ring", 200, "ring_silver.jpg"));
    }
}
