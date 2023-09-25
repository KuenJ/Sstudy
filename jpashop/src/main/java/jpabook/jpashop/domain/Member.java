package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter @Setter
public class Member {


        //기본키 만들면서 맵핑시켜버리는 것 @GeneratedValue incerease 1씩증가하는것 이랑같음 . 오라클의 identity
    @Id @GeneratedValue
    @Column(name = "meber_id")
    private  Long id;
    private  String name ;

    @Embedded
    //임베디드는 내가 만든자료형으로 맵핑하여 사용하겠다 할때 사용

    @OneToMany(mappedBy = "member")
//    일대다 의 관ㄱ ㅖ   멤버를 1대 다과계로 사용하겠다.

    private Address address;

    private List<Order> orders = new ArrayList<>();
}
