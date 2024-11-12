package com.example.project2.repository;

import static org.mockito.ArgumentMatchers.eq;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.project2.entify.jpql.Address;
import com.example.project2.entify.jpql.JpqlMember;
import com.example.project2.entify.jpql.Order;
import com.example.project2.entify.jpql.Product;
import com.example.project2.entify.jpql.QJpqlMember;
import com.example.project2.entify.jpql.QProduct;
import com.example.project2.entify.jpql.Team;
import com.example.project2.repository.jpql.JpqlmemberRepository;
import com.example.project2.repository.jpql.OrderRepository;
import com.example.project2.repository.jpql.ProductRepository;
import com.example.project2.repository.jpql.TeamRepository;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    private JpqlmemberRepository jpqlmemberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void insert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Team team = Team.builder().name("tema " + i).build();
            teamRepository.save(team);

            JpqlMember member = JpqlMember.builder().name("name " + i).age(i).team(team).build();
            jpqlmemberRepository.save(member);

            Product product = Product.builder().name("제품 " + i).price(i * 1000).stockAmount(i * 5).build();
            productRepository.save(product);

        });
    }

    @Test
    public void insertOrderTest() {

        Address address = new Address();
        address.setCity("서울시");
        address.setStreet("152-1");
        address.setZipcode("11017");
        // 2번 member가 3번 제품을 주문한다

        LongStream.rangeClosed(1, 3).forEach(i -> {

            Order order = Order.builder()
                    .address(address)
                    .orderAmount(15)
                    .member(JpqlMember.builder().id(2L).build())
                    .product(Product.builder().id(i).build())
                    .build();
            orderRepository.save(order);
        });
    }

    @Test
    public void testFindAll() {
        // jpqlmemberRepository.findMembers().forEach(m -> System.out.println(m));

        // 전체조회(오름차순)
        // System.out.println(jpqlmemberRepository.findMembers());

        // 정렬을 다른 컬럼
        // System.out.println(jpqlmemberRepository.findMembers(Sort.by("age")));
        // System.out.println(jpqlmemberRepository.findMembers(Sort.by(Sort.Direction.DESC,
        // "age")));

        List<Object[]> list = jpqlmemberRepository.findMembers2();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
            System.out.printf("name = %s, age = %d\n", objects[0], objects[1]);
        }
    }

    @Test
    public void testAddress() {
        System.out.println(orderRepository.findByAddress());
    }

    @Test
    public void testOrders() {
        List<Object[]> list = orderRepository.findByOrders();
        for (Object[] objects : list) {
            // System.out.println(Arrays.toString(objects));

            JpqlMember member = (JpqlMember) objects[0];
            Product product = (Product) objects[1];
            int orderAmount = (Integer) objects[2];

            System.out.println(member);
            System.out.println(product);
            System.out.println(orderAmount);
        }
    }

    @Test
    public void test() {
        // System.out.println(jpqlmemberRepository.findByName("name 1"));
        // System.out.println(jpqlmemberRepository.findByAgeGreaterThan(5));
        // System.out.println(jpqlmemberRepository.findByTeam(Team.builder().id(3L).build()));

        // List<Object[]> result = jpqlmemberRepository.aggregate();
        // for (Object[] objects : result) {
        // System.out.println(Arrays.toString(objects));
        // System.out.println("인원 후 : " + objects[0]);
        // System.out.println("나이 합계 : " + objects[1]);
        // System.out.println("나이 평균 : " + objects[2]);
        // System.out.println("연장자 : " + objects[3]);
        // System.out.println("최연소 : " + objects[4]);
        // }

        // System.out.println(jpqlmemberRepository.findByTeam2("tema 2"));

        List<Object[]> result = jpqlmemberRepository.findByTeam5("tema 1");
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            // System.out.println("member " + objects[0]);
            // System.out.println("team " + objects[1]);
        }

    }

    // querydsl 테스트
    @Test
    public void queryDslTest() {
        QProduct qProduct = QProduct.product;
        QJpqlMember member = QJpqlMember.jpqlMember;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // 상품명이 제품 1인 상품 조회
        // Iterable<Product> products = productRepository
        // .findAll(qProduct.name.eq("제품1"));

        // 상품명이 제품 1이고, 가경이 500초과인 상품 조회
        // Iterable<Product> products = productRepository
        // .findAll(qProduct.name.eq("제품 1")
        // .and(qProduct.price.gt(500)));

        // 상품명이 제품 1이고, 가경이 500이상인 상품 조회
        // Iterable<Product> products = productRepository
        // .findAll(qProduct.name.eq("제품 1")
        // .and(qProduct.price.goe(500)));

        // 상품명에 '제품' 글자가 들어있는 상품 조회
        // Iterable<Product> products = productRepository
        // .findAll(qProduct.name.contains("제품"));

        // 상품명에 '제품' 글자가 들어있는 상품 조회(BooleanBuilder 사용)
        booleanBuilder.and(qProduct.name.eq("제품 1")).and(qProduct.price.gt(500));
        Iterable<Product> products = productRepository.findAll(booleanBuilder);
        for (Product product : products) {
            System.out.println(product);
        }
        // 상품명이 '제품'으로 시작하는 상품 조회
        // Iterable<Product> products = productRepository
        // .findAll(qProduct.name.startsWith("제품"));

        // 상품명이 '3'으로 끝나는 상품 조회
        // Iterable<Product> products = productRepository
        // .findAll(qProduct.name.endsWith("3"));
        // for (Product product : products) {
        // System.out.println(product);
        // }

        // user name이 user3인 회원 조회
        // Iterable<JpqlMember> members = jpqlmemberRepository
        // .findAll(member.name.eq("name 3"));

        // user name이 user3인 회원 조회(id 기준으로 내림차순 정렬)
        // booleanBuilder.and(member.name.eq("name 3"));
        // Iterable<JpqlMember> members = jpqlmemberRepository
        // .findAll(booleanBuilder,
        // Sort.by("id").descending());
        // for (JpqlMember jpqlMember : members) {
        // System.out.println(jpqlMember);
        // }
    }
}
