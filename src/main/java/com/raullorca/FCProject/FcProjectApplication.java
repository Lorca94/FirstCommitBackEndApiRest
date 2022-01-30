package com.raullorca.FCProject;

import com.raullorca.FCProject.entity.AppUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication()
public class FcProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcProjectApplication.class, args);
	}

}
