package com.example.mart;

import java.time.LocalDateTime;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.constant.DeliveryStatus;
import com.example.mart.entity.constant.OrderStetus;
import com.example.mart.entity.item.Delivery;
import com.example.mart.entity.item.Item;
import com.example.mart.entity.item.Member;
import com.example.mart.entity.item.Order;
import com.example.mart.entity.item.OrderItem;
import com.example.mart.repository.item.DeliveryRepository;
import com.example.mart.repository.item.ItemRepository;
import com.example.mart.repository.item.MemberRepository;
import com.example.mart.repository.item.OrderItemRepository;
import com.example.mart.repository.item.OrderRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MartRepoditoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void memberInsertTest() {
        memberRepository.save(Member.builder().name("user1").city("서울시").street("187-12").zipcode("15100").build());
        memberRepository.save(Member.builder().name("user2").city("경기도").street("135-22").zipcode("17100").build());
        memberRepository.save(Member.builder().name("user3").city("부산시").street("653-69").zipcode("18100").build());
    }

    @Test
    public void itemInsertTest() {
        itemRepository.save(Item.builder().name("tshirt").price(25300).quantity(15).build());
        itemRepository.save(Item.builder().name("shoes").price(11300).quantity(20).build());
        itemRepository.save(Item.builder().name("pants").price(20000).quantity(10).build());
    }

    @Test
    public void orderInsertTest() {
        Member member = memberRepository.findById(1L).get();
        Item item = itemRepository.findById(2L).get();

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(OrderStetus.ORDER)
                .member(member)
                .build();
        orderRepository.save(order);

        OrderItem orderItem = OrderItem.builder()
                .price(200000)
                .count(2)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);

        // item 수량 감소
    }

    @Test
    public void memberAndItemAndOrderListTest() {
        // 주문내역 조회

        // orderRepository.findAll().forEach(order -> System.out.println(order));

        // 주문상품 상세 조회
        orderItemRepository.findAll().forEach(oi -> {
            System.out.println(oi);
            // 상품 상세 조회
            System.out.println(oi.getItem());
            // 주문 상세 내역 조회
            System.out.println(oi.getOrder());
            // 주문자 상세 조회
            System.out.println(oi.getOrder().getMember());
        });
    }

    @Test
    public void memberAndItemAndOrderRowTest() {

        OrderItem orderItem = orderItemRepository.findById(1l).get();

        // 주문상품 상세 조회
        System.out.println(orderItem);
        // 상품 상세 조회
        System.out.println(orderItem.getItem());
        // 주문 상세 내역 조회
        System.out.println(orderItem.getOrder());
        // 주문자 상세 조회
        System.out.println(orderItem.getOrder().getMember());

    }

    @Test
    public void memberAndItemAndOrderUpdateTest() {

        // 주문자의 주소 변경

        // Member member = Member.builder()
        // .id(1L)
        // .city("서울시")
        // .street("187-12")
        // .zipcode("15100")
        // .build();

        Member member = memberRepository.findById(1L).get();
        member.setStreet("189-12");
        // save : insert or update
        // 엔티티 매니져가 있어서 현제 entity 가 new 인지 기존 entity인지 구분이 가능함
        // new : insert 호출 / 기존 entity : update 호출
        // update는 무조건 전체 컬럼이 수전되는 형태롤 작성됨
        System.out.println(memberRepository.save(member));

        // 아이템 수량,가격 변경

        Item item = itemRepository.findById(2L).get();
        item.setPrice(32500);
        itemRepository.save(item);

        OrderItem orderI = orderItemRepository.findById(1L).get();
        orderI.setPrice(10000000);
        orderItemRepository.save(orderI);
    }

    @Test
    public void memberAndItemAndOrderDeleteTest() {
        // 주문 취소

        // 주문 상품 취소
        orderItemRepository.deleteById(1L);

        // 주문 취소
        orderRepository.deleteById(1L);
    }

    // 양방향
    // order ==> OrderItem 객체 그래프 탐색
    @Transactional
    @Test
    public void testOrderItemtList() {
        Order order = orderRepository.findById(2L).get();
        System.out.println(order);
        // Order ==> OrderItem 탐색 시도
        order.getOrderItemList().forEach(oi -> System.out.println(oi));
    }

    @Transactional
    @Test
    public void testOrderstList() {
        Member member = memberRepository.findById(1L).get();
        System.out.println(member);
        // Member ==> Order 탐색 시도
        member.getOrders().forEach(or -> System.out.println(or));
    }

    // 일대일
    @Test
    public void testDeliveryInsert() {
        // 배송 정보 입력
        Delivery delivery = Delivery.builder()
                .city("서울시")
                .streey("동소문로 1가")
                .zipcode("11501")
                .deliveryStatus(DeliveryStatus.READY)
                .build();

        deliveryRepository.save(delivery);

        // order와 배송정보 연결
        Order order = orderRepository.findById(2L).get();
        order.setDelivery(delivery);
        orderRepository.save(order);

    }

    @Test
    public void testOrderRead() {
        // order 조회(+배송조회)
        Order order = orderRepository.findById(2L).get();
        System.out.println(order);
        System.out.println(order.getDelivery());

    }

    @Test
    public void testDeliveryRead() {
        // order 조회(+배송조회)
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println(delivery);
        System.out.println(delivery.getOrder());

    }

}
