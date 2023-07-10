package com.cg.sakila.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.sakila.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Byte>{

}
