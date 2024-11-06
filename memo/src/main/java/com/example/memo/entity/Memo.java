package com.example.memo.entity;

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

// entify 패키지명 하단에 작성하는 클래스는 테이블 정의하는 것과 동일함
// memo 테이블 생성
// 메모번호(mno),메모내용(meno_text)
// Long => number(19) , String => varchar2(255) , int => number(10)

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
// 시퀀스 생성
@SequenceGenerator(name = "memo_seq_gen", sequenceName = "memo_seq", allocationSize = 1)
@Entity // table 생성
public class Memo extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memo_seq_gen")
    @Id // pk 생성
    private Long meno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
