package com.example.board.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = { "writer", "replies" })
@Setter
@Getter
@Entity
@Table(name = "board_id")
public class Board extends BaseEntity {
    @SequenceGenerator(name = "board_id_seq_gen", sequenceName = "board_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_id_seq_gen")
    @Id
    private Long bno;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE) // 기본 Fatch 전략이 LAZY
    private List<Reply> replies = new ArrayList<>();

}
