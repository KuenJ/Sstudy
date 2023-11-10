package org.zerock.b01.domain;


import lombok.*;

import javax.persistence.*;

@Entity             //이 어노테이션은 해당 클래스가 JPA 엔터티임을 나타냅니다. 즉, 데이터베이스의 테이블과 매핑될 클래스라는 것을 의미합니다.
@Table(name = "Reply",indexes = {
        @Index(name = "idx_reply_board_bno",columnList = "board_bno")
})
//Table 어노테이션은 JPA(Java Persistence API)에서 사용되며, 엔터티 클래스가
// 데이터베이스 테이블과 매핑될 때 테이블에 대한 세부적인 설정을 지정하는 데 사용됩니다.



@Getter             //Lombok 어노테이션으로, 모든 필드에 대한 getter 메서드를 자동으로 생성합니다.
@Builder            //어노테이션으로, 빌더 패턴을 생성하여 객체를 생성할 때 더 가독성 있게 만듭니다.
@AllArgsConstructor  //ombok 어노테이션으로, 각각 모든 필드를 포함한 생성자
@NoArgsConstructor  // 매개변수가 없는 생성자를 생성합니다.
@ToString(exclude = "board")  //toString 메서드를 생성하면서 board 필드를 toString에서 제외합니다.
public class Reply extends BaseEntity {

    @Id   //이 두 어노테이션은 해당 필드(rno)가 엔터티의 기본 키이며,
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스에서 자동으로 값을 생성하도록 지정합니다. 이 경우에는 데이터베이스의
                                                        // auto-increment 혹은 identity와 관련이 있어서 기본 키 값이 자동으로 증가됩니다

    private  Long rno;


    @ManyToOne(fetch = FetchType.LAZY) // 이 어노테이션은 다대일(N:1) 관계를 표시합니다.
                                        //reply 엔터티가 Board 엔터티에 대한 다대일 관계를 가지고 있다는 것을 나타냅니다.
    private Board board;                //fetch = FetchType.LAZY는 게으른 로딩을 사용하여 성능을 최적화하려는 의도를 나타냅니다.
                                        // 즉, Reply를 조회할 때 Board는 필요할 때까지 로딩하지 않습니다.

    private String replyText;

    private  String replyer;

}
