
package com.example.movie.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@ToString(exclude = { "movieImages", "reviews" })
@Setter
@Getter
@Entity
@Table(name = "movie_id")
public class Movie extends BaseEntity {

    @SequenceGenerator(name = "movie_id_seq_gen", sequenceName = "movie_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_seq_gen")
    @Id
    private Long mno;

    @Column(nullable = false)
    private String title;

    // 자식 연관관계 추가(양방향)
    // @Builder.Default
    // @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    // List<MovieImage> movieImages = new ArrayList<>();

    // @Builder.Default
    // @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    // List<Review> reviews = new ArrayList<>();
}
