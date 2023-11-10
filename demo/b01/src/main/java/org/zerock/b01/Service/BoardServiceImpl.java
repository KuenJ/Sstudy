package org.zerock.b01.Service;


import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.repository.BoardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional  //연산이 고립되어, 다른 연산과의 혼선으로 인해 잘못된 값을 가져오는 경우가 방지된다.
//연산의 원자성이 보장되어, 연산이 도중에 실패할 경우 변경사항이 Commit되지 않는다.
public class BoardServiceImpl implements BoardService {


    private final ModelMapper modelMapper; //객체간 데이터 전환을 도와주는 라이브러리 이곳에서는 boardDTO객체와 BOARD 엔티티객체간에 매핑을수행하기위해 사용된다.
    private final BoardRepository boardRepository; //boardRepository 필드를 통해 게시판과 관련된 데이터베이스 작업을 수행합


    @Override
    //등록기능
    public Long register(BoardDTO boardDTO) {  //데이터 저장하는 메서드인데 BoardDTO의 객체를 받아서 해당 데이터를 DB에 저장하는 메서드

        Board board = modelMapper.map(boardDTO, Board.class);  //먼저 modelMapper의 객체를사용하여 BoardDTO 객체를 Board엔티티로 매핑한다
        //modelmppaer.map()메서드는 board dto의 정보를 board로
        // 복사또는 매핑하는데 사용된다 이렇게하므로 board dto 가  board 엔티티로 변환되어 db저장가능
        Long bno = boardRepository.save(board).getBno();
        //변환된 board엔티티를 boardrespository에 저장하고 저장된 엔티티 반환
        // getBno()메서드를 호출하여 board엔티티 의 고유식별ㅈ자인 bno를 추출
        return bno;
    }

    @Override
    //조회기능
    public BoardDTO readOne(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);   //
        Board board = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;

        /**
         *optional은 자바 8에서 소개된 클래스로, 값이 존재할 수도 있고 존재하지 않을 수도 있는 상황에서 사용되는 컨테이너(Wrapper)
         * 클래스입니다. Optional은 코드에서 명시적으로 값의 존재 여부를 처리하고, null을 방지하며, 값이 없을 때 예외 처리를
         * 용이하게 만들어줍니다
         *
         *
         * Board board = result.orElseThrow();: 이 줄은 Optional에 값이 있는지 확인하고, 값이 없을 경우 예외를 발생시킵니다.
         * orElseThrow 메서드는 Optional이 비어있을 때
         * 지정된 예외를 발생시키는 역할을 합니다. 이것은 데이터베이스에서 게시물을 찾을 수 없는 경우에 예외를 발생시켜 처리자에게 알립니다.
         *
         *
         *
         * BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);: 이 줄은 게시물 엔티티(Board)를 BoardDTO
         * 객체로 매핑하는 역할을 합니다. modelMapper는 객체 간 변환을 도와주는 매핑 라이브러리일 것입니다.
         * map 메서드는 Board 객체를 BoardDTO 객체로 변환합니다. 이것은 게시물 엔티티와 데이터 전송 객체 간의 필드 매핑을 수행합니다.
         * return boardDTO;: 이 줄은 변환된 BoardDTO 객체를 반환합니다. 이제 검색한 게시물을 BoardDTO 형식으로 비즈니스 계층에서 반환할 수 있게 됩니다.
         * */
    }


    //수정 코드
    @Override
    public void modify(BoardDTO boardDTO) {

        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());

        boardRepository.save(board);
        /**
         * Optional<Board> result = boardRepository.findById(boardDTO.getBno());:
         * 이 줄은 수정하려는 게시물을 데이터베이스에서 조회합니다.
         * boardRepository는 데이터 액세스 객체로, findById 메서드를 사용하여 게시물을 Optional<Board> 형식으로 가져옵니다.
         * boardDTO.getBno()를 사용하여 게시물의 식별자를 얻어 데이터베이스에서 해당 게시물을 찾습니다.*
         *
         *Board board = result.orElseThrow();: 이 줄은 Optional에 값이 있는지 확인하고,
         * 값이 없을 경우 NoSuchElementException 예외를 던집니다.
         * 이것은 데이터베이스에서 게시물을 찾을 수 없는 경우에 예외를 발생시켜 처리자에게 알립니다.
         *
         *board.change(boardDTO.getTitle(), boardDTO.getContent());:
         *  Board 객체의 change 메서드를 호출하여 게시물의 내용을 변경합니다.
         *  이 메서드는 Board 엔티티에서 게시물의 제목과 내용을 업데이트하는 역할을 수행합니다.
         *
         * boardRepository.save(board);: 마지막으로 수정된 Board 엔티티를 데이터베이스에 저장합니다.
         * 이것은 수정된 내용을 데이터베이스에 반영하는 과정입니다.
         *
         *
         * 이 코드는 게시물을 수정하기 위한 일반적인 절차를 따르며,
         * 데이터베이스 조회, 업데이트 및 저장 과정을 포함합니다
         * . Optional을 사용하여 게시물을 찾고, 업데이트된 데이터를 Board 객체로 반영한 후,
         * 변경 내용을 데이터베이스에 저장합니다.
         * **/

    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }


    @Override
    // 페이지 및 검색 조건을 이용하여 게시물 목록을 검색하고, 결과를 PageResponseDTO<BoardDTO> 형태로 반환하는 메서드입니다.
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {


        String[] types = pageRequestDTO.getTypes();  // PageRequestDTO에서 검색 유형(type)을 가져옵니다.
        String keyword = pageRequestDTO.getKeyword();  // PageRequestDTO에서 검색 키워드(keyword)를 가져옵니다.
        Pageable pageable = pageRequestDTO.getPageable("bno");  // 페이지 및 정렬 정보를 가져옵니다.

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
        // BoardRepository를 사용하여 게시물 목록을 검색하고, 검색 결과를 Page<Board> 형태의 result 변수에 저장합니다.


        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

}
