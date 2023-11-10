package org.zerock.b01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.QBoard;

import java.util.List;

public class BoardSearchImpl  extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl(){
        super(Board.class);

    }
    //BoardSearchImpl 클래스가 어떤 엔티티(Board)에 대한 검색을 지원하는지설정  여기에서
    //Board.class를 전달해서 Board엔티티에 대한 검색을  처리한다 .
    @Override
    public Page<Board> search1(Pageable pageable){
        QBoard board = QBoard.board; //도메인 객체
        JPQLQuery<Board> query = from(board);// Querydsl에서 사용할 쿼리 객체를 생성하고 이를 board 도메인객체로 초기화 //이쿼리객체를 사용하여 쿼리를 구성하고실행
        BooleanBuilder booleanBuilder =new BooleanBuilder(); //(

        booleanBuilder.or(board.title.contains("11"));//title like..
        booleanBuilder.or(board.content.contains("11"));//content lik

        query.where(booleanBuilder);
        query.where(board.bno.gt(0L));

        //query.where(board.title.contains("1"));//where title like. .. //조건 1항목을 찾는거임 like와 비슷한 역할
        //paging
        this.getQuerydsl().applyPagination(pageable,query);


        List<Board> list =query.fetch(); //기능사용뒤에

        long count =query.fetchCount();//이것을 작성하면 실행가능


        return null;
    }

    @Override
    //리턴타입 Page<Board> Board 객체를 포함하는 페이지 형태로결과를 반환 .
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {


        //Querydsl을 사용하기위한설정      2개 생성해서 사용
        QBoard board =QBoard.board;
        JPQLQuery<Board> query =from(board);

        if (( types != null && types.length>0) && keyword != null){//검색조건과 키워드가 있다면

                BooleanBuilder  booleanBuilder = new BooleanBuilder(); //(

            for (String type: types){  //검색 조견별로 조건추가 for루프 사용해서 검색조건 순회하며 해당조건에따라 연산자 사용하여 조건 추가

                switch (type){

                    case "t" :
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case  "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;

                    case  "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }   //end for
            query.where(booleanBuilder);
        }//end if
        //bno>0
        query.where(board.bno.gt(0L));//게시판번호가 0보다 큰게시물만 검색가능하게 하는것


        //paging 처리
        this.getQuerydsl().applyPagination(pageable, query);//페이징정보를 쿼리에 적용 이를통해 요청된 페이지와 페이지크기에 따라 결과제한가능

        //쿼리실행
        List<Board> list = query.fetch();  // 쿼리를 실행하고 ,결과를 리스트로 가져옴
        long count = query.fetchCount();  //사용하여 전체결과의개수를 가져옴

        return  new PageImpl<>(list,pageable,count);
        /**
         * 검색결과를 page<board> 형태로 래핑하여  반환한다
         * PageImpl 생성자를 사용하여 검색결과 ,페이징정보 ,  총개수등을 포함한 Page객체를 생성하고 반환한다.
          */
    }
}






















//이메서드는 BoardSearch 인터페이스의 search1메서드를 구현 실제로는 데이터베이스에서 게시판의 데이터를
//검색하고 결과를 페이지네이션하여 반환하는 코드가 이메서드내에 구현되어야함 이예제는 null을 반환하고 있으므로 실제 검색로직구현 x
// 보다 구체적인 검색로직은 Querydsl또는 SpringDataJpa를 사용하여 데이터베이스에서 필요한 데이터를 조회하고
//그결과를형식으로 반환하는 방식으로 구현되어야 합니다. 이 클래스는 QuerydslRepositorySupport를
//  //확장하므로 Querydsl을 사용하여 복잡한 쿼리를 작성하고 실행할 수 있습니다.