package com.hedlundkaua.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hedlundkaua.course.entities.User;
import com.hedlundkaua.course.repositories.UserRepository;

@Configuration //para falar para o spring que essa classe é uma de config usamos essa anotation
@Profile("test") //para falar que essa classe vai ser especifica para o perfil de teste usamos essa anotation
public class TestConfig implements CommandLineRunner{
	
	@Autowired //usado para resolver injeção de dependência e associar uma instância de UserRepository.
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
	}
	
	
	
	
	
}