package jpabook.jpashop.Repository;


import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {


    @PersistenceContext  //영속적으로 데이터를 가져와야함
    private EntityManager em;

    public void save(Member member){
        em.persist(member);  //저장
    }

    public  Member findOne(Long id){
        return  em.find(Member.class,id);  //id 값으로 찾기
    }

    public List<Member> findAll(){

            return  em.createQuery("select m from Member m",Member.class)
                    .getResultList();
    }

    public  List<Member> findByName(String name){


        return em.createQuery("select m from Member m  where m.name = :name",Member.class)
                .setParameter("name",name).getResultList();
    }

    //@Repository : 스프링 빈으로 등록, jpa 예외 스프링 기반 예외로 변환 한다.
    // @PersistenceContex: 엔티티 매니저 주입
    //@PersistenceUnit: 엔티티 매니저 팩토리 주입 ;

//다음에해야할걸  서비스개발
    // 상품도메인개발
    // 상품 엔티티 개발 (비즈니스로직)
    // 상품 레포지토리 개발 .

}
