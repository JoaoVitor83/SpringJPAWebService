package com.embosoft.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.embosoft.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
