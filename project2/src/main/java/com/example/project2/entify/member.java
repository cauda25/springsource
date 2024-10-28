package com.example.project2.entify;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.project2.entify.constant.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원 테이블
// id,username,age
// 회원가입일, 수정일 필요
// 회원 - 관리자/회원 구분
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "membertbl")
public class member {

    @Id
    private String id;
    @Column(name = "name")
    private String username;

    private int age;

    private RoleType roleType;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}