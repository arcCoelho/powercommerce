package com.dstech.powercomerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PowercomerceApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PowercomerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-----> senha: "+passwordEncoder.encode("123456"));

		Boolean result = passwordEncoder.matches("123456", "$2a$10$iJZty3QzVOpMVP3A/CpTKemKVejWvUiAK.PDIr.IDCjLvxk9jb9wW");
		System.out.println("a senha confere!? "+result);
	}
}