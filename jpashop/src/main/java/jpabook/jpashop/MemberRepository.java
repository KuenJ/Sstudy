package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
//    Persiten는 엔티티에서 사용한다 직접연결하거나 가지고올때사용
//    생성반환 주입
    @PersistenceContext
    EntityManager em;

    public  Long save(Member member){
        em.persist(member);
        return member.getId();
    }
    public Member find(Long id){
        return   em.find(Member.class, id);

    }
}
