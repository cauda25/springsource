package com.example.todo.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name = "todo_seq_gen", sequenceName = "todo_seq", allocationSize = 1)
@Entity(name = "todotbl")
public class Todo {

    // embedded database (H2, HSQL or Derby), please put it on the classpath
    // ==> application.properties 설정 필요

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_seq_gen")
    @Column(name = "todo_id")
    @Id
    private Long id;

    @ColumnDefault("0")
    private boolean completed;

    @ColumnDefault("0")
    private boolean important;

    private String title;

}
