package com.hedlundkaua.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hedlundkaua.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
}
