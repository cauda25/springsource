package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.Category;

public interface CategoryRepsitory extends JpaRepository<Category, Long> {

}