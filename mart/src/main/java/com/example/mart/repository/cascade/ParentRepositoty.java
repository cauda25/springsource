package com.example.mart.repository.cascade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entity.cascade.Parent;

public interface ParentRepositoty extends JpaRepository<Parent, Long> {

}
