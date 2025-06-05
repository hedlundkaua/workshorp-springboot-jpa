package com.hedlundkaua.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hedlundkaua.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
