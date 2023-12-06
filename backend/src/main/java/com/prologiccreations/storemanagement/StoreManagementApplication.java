package com.prologiccreations.storemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class StoreManagementApplication {

	public static ApplicationContext context;
	public static void main(String[] args) {
		context = SpringApplication.run(StoreManagementApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}

	public static String getCurrentUsername(){
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}


}
