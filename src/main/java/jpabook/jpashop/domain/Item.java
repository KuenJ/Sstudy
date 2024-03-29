package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter@Setter
public abstract class Item {
//추상화 실제로 가져다쓰진않음


    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;

    private int price;

    private  int stockQuantity;

    @ManyToMany(mappedBy = "items")

    private List<Category> categories = new ArrayList<Category>();

}
