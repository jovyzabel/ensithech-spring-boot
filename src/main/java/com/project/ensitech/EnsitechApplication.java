package com.project.ensitech;

import com.project.ensitech.model.entity.User;
import com.project.ensitech.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EnsitechApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnsitechApplication.class, args);
	}



}
