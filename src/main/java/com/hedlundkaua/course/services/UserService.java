package com.hedlundkaua.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hedlundkaua.course.entities.User;
import com.hedlundkaua.course.repositories.UserRepository;
import com.hedlundkaua.course.services.exceptions.DatabaseException;
import com.hedlundkaua.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@Service //instancia como componente do Spring
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){ //operação na camada de serviço que ela repassa a chamada para o repository.findAll 
		return repository.findAll();
	}
	
	public User findById(Long Id) {
		Optional<User> obj = repository.findById(Id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(Id));
	}
	
	//retorna o usuario salvo
	public User insert(User obj){
		return repository.save(obj); //esse save por padrao ja retorna o obj salvo
	}
	
	//deleta o usuario pelo ID
	public void delete(Long id) {
		if (!repository.existsById(id)) { // Verifica se o ID existe antes de tentar deletar
	        throw new ResourceNotFoundException(id);
	    }
	    try {
	        repository.deleteById(id);
	    } catch (DataIntegrityViolationException e) {
	        throw new DatabaseException(e.getMessage());
	    }
	}
	
	public User update(Long id, User obj) {
		try {
		User user = repository.getReferenceById(id); // ele instancia um usuario, mas não vai ao banco de dados ainda, ele deixa o obj monitorado pelo JPA e em seguida podemos efetuar alguma operação no banco de dados.
		//o findById vai diretamente ao banco de dados 
		updateData(user, obj); //função
		return repository.save(user);//salvo no banco de dados
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		user.setPhone(obj.getPhone());
	}
}
