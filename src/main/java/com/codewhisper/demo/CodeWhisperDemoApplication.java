package com.codewhisper.demo;

import com.codewhisper.demo.dto.UserDto;
import com.codewhisper.demo.entity.Product;
import com.codewhisper.demo.entity.Role;
import com.codewhisper.demo.entity.User;
import com.codewhisper.demo.repository.ProductRepository;
import com.codewhisper.demo.repository.RoleRepository;
import com.codewhisper.demo.repository.UserRepository;
import com.codewhisper.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class CodeWhisperDemoApplication implements CommandLineRunner{

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {

		SpringApplication.run(CodeWhisperDemoApplication.class, args);
	}

	//load user data into database using name, username, password and email, use indian names
	@Override
	public void run(String... args) throws Exception {
		roleRepository.save(new Role("ADMIN"));
		roleRepository.save(new Role("USER"));
		userService.saveAdmin(new UserDto( "admin", "admin", "admin", "test@test.com"));
		userService.saveUser(new UserDto( "Sandeep", "sandeep", "sandeep", "sandeep@test.com"));
		userService.saveUser(new UserDto( "Raj", "raj", "raj", "raj@test.com"));
		productRepository.save(new Product("ring1","Diamond ring",2000));
		productRepository.save(new Product("ring2","Gold ring",1000));
		productRepository.save(new Product("ring3","Silver ring",200));

	}
}
