package com.example.mart.entity.item;

import java.util.ArrayList;
import java.util.List;

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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "orders")
@Setter
@Getter
@Table(name = "mart_member")
@Entity
@SequenceGenerator(name = "mart_member_seq_gen", sequenceName = "mart_member_seq", allocationSize = 1)
public class Member extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mart_member_seq_gen")
    @Column(name = "member_id")
    @Id
    private Long id;

    private String name;

    private String zipcode;

    private String city;

    private String street;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
