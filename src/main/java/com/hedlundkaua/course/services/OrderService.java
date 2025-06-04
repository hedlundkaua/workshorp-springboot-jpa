package com.hedlundkaua.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hedlundkaua.course.entities.Order;
import com.hedlundkaua.course.repositories.OrderRepository;


@Service //instancia como componente do Spring
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll(){ //operação na camada de serviço que ela repassa a chamada para o repository.findAll 
		return repository.findAll();
	}
	
	public Order findById(Long Id) {
		Optional<Order> obj = repository.findById(Id);
		return obj.get();
	}
	
}
