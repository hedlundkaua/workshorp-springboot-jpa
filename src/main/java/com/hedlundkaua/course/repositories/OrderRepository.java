package com.hedlundkaua.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hedlundkaua.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{ //os repositores vão ser as interfaces
//só isso instancia um objeto repository que tem varias operações para trabalhar com o usuario
	
//Não precisa criar a implementação porque o SpringJpa tem uma implementação para a interface
//ja temos uma implementação padrão	
}
