package com.example.guestbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "guestbook_id")
public class GuestBook extends BaseEntity {
    @SequenceGenerator(name = "guestbook_id_seq_gen", sequenceName = "guestbook_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guestbook_id_seq_gen")
    @Id
    private Long gno;

    @Column(length = 50, nullable = false)
    private String writer;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

}
