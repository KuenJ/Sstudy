package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    //@id는 pk라는의미
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    //    다대일관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")

    private Member member;//주문회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // 영속성 전이 부모엔티티가 영소화될대 자식도 같이된다  엔티티끼리 상속되는것
    private List<OrderItem> orderItems = new ArrayList<>(); //상품주문

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //Lazy는 지연로딩 선작업이
    @JoinColumn(name = "delivery_id")

    private Delivery delivery; //배송정보

    private LocalDateTime orderDate; //주문시간



    @Enumerated(EnumType.STRING) //나열되어있다면 주문상태 확인 가능하기에 사용
    private OrderStatus status; // 주문상태   [Order,CANCEL];

    //
    //연관관계 메서드

    public  void setMember(Member member){
            this.member = member;
            member.getOrders().add(this);
    }
    public  void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public  void setDelivery(Delivery delivery){
        this.delivery =delivery;
        delivery.setOrder(this);
    }


}
