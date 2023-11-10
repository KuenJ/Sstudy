package org.zerock.b01.Service;


import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);  // 캡슐화,효율적데이터전송,가독성및 유지보수성 따라서 "조회" 메서드의 반환 타입이 DTO인 경우, 데이터를 비즈니스 계층에서
                                    // 데이터 액세스 계층 또는 다른 서비스로 전달하기 위해 데이터를 캡슐화하고 구조를 표현하는 좋은 방법입니다.

    void modify(BoardDTO boardDTO);
    /**
     * 수정" 메서드의 반환 타입이 void인 이유는 일반적으로 데이터 수정 작업이 성공했는지 실패했는지 확인하기
     * 위한 반환 값이 필요하지 않기 때문입니다.
     * "수정" 메서드의 역할은 주어진 데이터를 업데이트하고 데이터베이스나 저장소에
     * 변경 사항을 반영하는 것입니다. 이 작업이 성공적으로 수행되면 반환할 것이 없습니다.*/



    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
    /**
     * 이 메서드는 게시물 목록을 페이지별로 검색하고, 검색 결과를 PageResponseDTO<BoardDTO> 형태로 반환하는 역할을 합니다.
     * 여기서 각 매개변수와 반환값을 간단히 설명하겠습니다:
     * PageRequestDTO pageRequestDTO: 페이지 및 검색 조건을 나타내는 객체로,
     * 현재 페이지 번호, 페이지 크기, 검색 유형(type), 검색 키워드(keyword) 등을 포함합니다.
     * PageResponseDTO<BoardDTO>: 검색 결과를 담는 객체로, 게시물 정보를 나타내는 BoardDTO를 포함하고,
     * 화면에 표시할 페이지 정보(시작 페이지, 끝 페이지, 이전 페이지 여부, 다음 페이지 여부)를 담고 있습니다.
     * 결과적으로 이 메서드는 pageRequestDTO에 따라 게시물을 검색하고, 검색 결과와 페이지 정보를 PageResponseDTO<BoardDTO> 형태로 반환합니다.
     * 이를 통해 웹 애플리케이션에서 페이지별로 게시물을 효과적으로 표시할 수 있습니다.
     */
}

