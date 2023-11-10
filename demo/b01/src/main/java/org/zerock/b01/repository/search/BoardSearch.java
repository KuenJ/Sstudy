package org.zerock.b01.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.b01.domain.Board;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);
    //이메서드는 게시판데이터를 페이지네이션하여 검색하기위한 메서드 Pageable매개변수를 사용하여
    //페이지 설정을 전달할수 있으며 ,Page<Board>를 반환하므로 검색결과가 페이지단위로 반환된다.

    Page<Board> searchAll(String[] types, String keyword,Pageable pageable);

}
