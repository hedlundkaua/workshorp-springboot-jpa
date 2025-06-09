package com.hedlundkaua.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hedlundkaua.course.entities.User;
import com.hedlundkaua.course.repositories.UserRepository;


@Service //instancia como componente do Spring
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){ //operação na camada de serviço que ela repassa a chamada para o repository.findAll 
		return repository.findAll();
	}
	
	public User findById(Long Id) {
		Optional<User> obj = repository.findById(Id);
		return obj.get();
	}
	
	//retorna o usuario salvo
	public User insert(User obj){
		return repository.save(obj); //esse save por padrao ja retorna o obj salvo
	}
	
	//deleta o usuario pelo ID
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
