package com.example.memo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(value = { AuditingEntityListener.class })
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDateTime; // 최초 생성 시간

    @LastModifiedDate
    private LocalDateTime lastModifyDateTime; // 최초 수정 시간
}
