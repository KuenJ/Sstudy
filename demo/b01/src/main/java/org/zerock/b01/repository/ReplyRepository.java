package org.zerock.b01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where  r.board.bno = :bno")
    Page<Reply> listOfBoard(Long bno, Pageable pageable);
}
/**ReplyRepository는 JpaRepository<Reply, Long>를 상속받고 있습니다.
 * 이것은 Spring Data JPA에서 제공하는 기본적인 CRUD(Create, Read, Update, Delete)
 * 기능을 지원하는 JpaRepository입니다. Reply는 엔터티 타입이며, Long은 해당 엔터티의 기본 키 타입입니다.

 @Query 어노테이션은 JPQL(Java Persistence Query Language)을 사용하여 직접 쿼리를 정의할 때 사용됩니다.
 여기서는 listOfBoard라는 메서드를 정의하고 있습니다.

 JPQL 쿼리: "select r from Reply r where r.board.bno = :bno"

 select r from Reply r: Reply 엔터티의 모든 필드를 조회합니다. r은 엔터티에 대한 별칭(alias)입니다.
 where r.board.bno = :bno: 이 부분은 특정 조건을 나타냅니다.
 여기서는 board 속성 중에서 bno가 파라미터로 전달된 :bno 값과 일치하는 경우를 찾습니다.
 Page<Reply>: 조회된 결과를 페이지로 반환합니다.
 Spring Data JPA에서 제공하는 Page는 페이징 처리된 데이터를 표현합니다.

 listOfBoard(Long bno, Pageable pageable): 메서드의 파라미터로는 bno와 pageable이 있습니다.

 Long bno: 게시물 번호(bno)를 기준으로 댓글을 조회하기 위한 파라미터입니다.
 Pageable pageable: 페이징 및 정렬 정보를 담고 있는 객체로,
 클라이언트에서 요청한 페이지 번호, 페이지 크기, 정렬 조건 등을 처리합니다.
 이 코드는 특정 게시물 번호(bno)에 해당하는 댓글을 페이징 처리하여 조회하는 메서드를 정의한 것입니다.
 Spring Data JPA의 강력한 기능을 이용하여 간단하면서도 효과적인 데이터베이스 조회를 수행할 수 있게 해줍니다.
 * */