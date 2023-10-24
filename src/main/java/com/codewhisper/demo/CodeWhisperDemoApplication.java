package com.codewhisper.demo;

import com.codewhisper.demo.entity.User;
import com.codewhisper.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class CodeWhisperDemoApplication implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {

		SpringApplication.run(CodeWhisperDemoApplication.class, args);
	}

	//load user data into database using name, username, password and email, use indian names
	@Override
	public void run(String... args) throws Exception {
			userRepository.save(new User( "admin", "admin", "admin", "test@test.com"));
			userRepository.save(new User( "Sandeep", "sandeep", "sandeep", "sandeep@test.com"));
			userRepository.save(new User( "Raj", "raj", "raj", "raj@test.com"));

	}
}
