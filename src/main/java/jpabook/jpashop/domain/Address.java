package jpabook.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){ //자바기본생성자는 프로텍티드로선언프로텍티드는 상속받아서 수정가능기능이됨 . 하는이유는 setter가없기때문에 이방법으로 접근한다 ?

    }

    public Address(String city,String street,String zipcode){
        this.city=city;
        this.street=street;
        this.zipcode=zipcode;
    }

}

/*
1.엔티티에는 가급적이면 setter 사용하지말자 (테이블과매핑되기때문에):
    :(예를 들어 setter가 다있다고 침)
    :변경/수정 될수 있는 포인트가 너무 많음
    :유지보수가 어렵다
    :리팩토링할때/setter를 제거한다. (처음에는 만들어놓고 나중에제거해줘여함)
2.

 */
