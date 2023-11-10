package org.zerock.b01.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass  // 이클래스는 데이터 베이스 테이블을 생성하지않고 다른 엔티티클래스에서 재사용된다. 즉 주입불가능하고 extends로 상속해 서속성사용가능
@EntityListeners(value = {AuditingEntityListener.class})
@Getter

//@EntityListeners(value = {AuditingEntityListener.class}) 를 적용하면 엔티티가 데이터베이스에 추가되거나 변경될때 자동으로 시간값 저장이가능.
abstract class BaseEntity {

    @CreatedDate  // 새로운 엔티티가 db에 저장될때 ,regdate필드에 현재시간을 자동으로 저장한다 .
    @Column(name =  "regdate",updatable = false)  // updatable=false로 설정되있으므로 이필드는 업데이트 불가 db에저장되면 그값은 변경되지않음.
    private LocalDateTime regDate;


    @LastModifiedDate    //엔티티가 데이티베이스에서 수정될때 modDate필드에 현재시간을 자동으로업데이트함
    @Column(name = "moddate")//moddate필드는 db에서 업데이트 작업이 발생할때마다 해당시간으로 갱신된다 .
    private LocalDateTime modDate;

}
