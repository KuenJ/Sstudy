package org.zerock.b01.domain;


import lombok.*;

import javax.persistence.*;

@Entity //엔티티 클래스 정의 Board 클래스가 엔티티로 지정되어서 이클래스는 db테이블과매핑된다.
@Getter //Getter 어노테이션은 Lombok 라이브러리에서 제공되며, Getter 메서드를 자동으로 생성합니다. 이렇게 생성된 Getter 메서드는 해당 필드의 값을 가져오는데 사용됩니다.
@Builder//노테이션은 Lombok의 기능으로, 빌더 패턴을 자동으로 생성합니다. 이것은 객체를 생성할 때 편리하게 사용할 수 있도록 해줍니다.
@AllArgsConstructor   // 어노테이션은 Lombok에서 제공되며, 모든 필드를 인자로 받는 생성자를 자동으로 생성합니다.
@NoArgsConstructor   //어노테이션은 Lombok에서 제공되며, 파라미터 없는 기본 생성자를 자동으로 생성합니다.
@ToString           //toString 메서드를 자동으로 생성하여 객체의 문자열 표현을 반환합니다.

public class Board extends BaseEntity{


    @Id       //이어노테이션은  해당  필드가 엔티티의 식별자(PK) 임을 나타낸다 bno필드가 엔티티 식별자로 사용된다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //GeneratedValue 어노테이션은 데이터베이스의 자동 증가(primary key 자동 생성) 전략을 지정합니다.
                                                            //strategy = GenerationType.IDENTITY는 데이터베이스에서 자동으로 고유 식별자를 생성하도록 설정합니다.

    private Long bno;

    @Column(length = 500, nullable = false)// @Column 어노테이션은 엔티티 필드와 데이터베이스 칼럼 간의 매핑을 지정합니다.
    private String title;

    @Column(length = 2000,nullable = false) //nullable 속성은 해당 칼럼이 null 값을 허용하는지를 나타냅니다
    private String content;


    @Column(length = 50,nullable = false)
    private String writer;

    public void change(String title,String content){
        this.title =title;
        this.content = content;
    }
}
