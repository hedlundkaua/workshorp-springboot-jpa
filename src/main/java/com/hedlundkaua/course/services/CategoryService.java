package com.hedlundkaua.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hedlundkaua.course.entities.Category;
import com.hedlundkaua.course.repositories.CategoryRepository;


@Service //instancia como componente do Spring
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){ //operação na camada de serviço que ela repassa a chamada para o repository.findAll 
		return repository.findAll();
	}
	
	public Category findById(Long Id) {
		Optional<Category> obj = repository.findById(Id);
		return obj.get();
	}
	
}
