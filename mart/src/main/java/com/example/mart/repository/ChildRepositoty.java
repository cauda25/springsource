package com.example.mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entity.cascade.Child;

public interface ChildRepositoty extends JpaRepository<Child, Long> {

}
