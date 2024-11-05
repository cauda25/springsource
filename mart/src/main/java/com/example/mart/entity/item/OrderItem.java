package com.example.mart.entity.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "item", "order" })
@Setter
@Getter
@Entity
@Table(name = "mart_order_item")
@SequenceGenerator(name = "mart_order_item_seq_gen", sequenceName = "mart_order_item_seq", allocationSize = 1)
public class OrderItem extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mart_order_item_seq_gen")
    @Column(name = "order_item_id")
    @Id
    private Long id;

    private int price;

    private int count;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;
}
