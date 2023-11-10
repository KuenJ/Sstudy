package org.zerock.b01.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.Service.BoardService;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

import javax.validation.Valid;

@Controller //웹플리케이션과 관련된 HTTP 요청처리하며 HTTP응답생성
@RequestMapping("/board") //컨트롤러를 기본 URL경로에 매핑하는데 사용
@RequiredArgsConstructor

public class BoardController {
    private final BoardService boardService;


    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
//        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    // "/register" 경로에 GET 요청을 처리하는 메서드입니다.
    @GetMapping("/register")
    public void register() {
        // 이 메서드는 등록 페이지를 표시하기 위한 GET 요청을 처리합니다.
    }

    // "/register" 경로에 POST 요청을 처리하는 메서드입니다.
    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //BindingResult는 스프링 프레임워크에서 사용되는 유효성 검사(validation) 결과를 저장하고 관리하는 객체입니다
        // 이 메서드는 게시판(Board) 정보를 등록하기 위한 POST 요청을 처리합니다.

        // 유효성 검사 결과를 확인합니다.
        if (bindingResult.hasErrors()) {
            // 만약 입력 데이터에 유효성 검사에서 문제가 발생한 경우, 다음 코드가 실행됩니다.

            // 발생한 모든 에러들을 RedirectAttributes를 통해 다음 요청으로 전달합니다.
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            // 사용자를 "/board/register" 경로로 리다이렉트하여 에러 메시지를 표시합니다.
            return "redirect:/board/register";
        }

        // 에러가 발생하지 않은 경우, 게시판 정보를 등록합니다.
        Long bno = boardService.register(boardDTO); // 게시판 정보 등록 메서드 호출

        // 게시판 정보 등록이 성공하면, 등록된 게시물 번호를 RedirectAttributes를 통해 다음 요청으로 전달합니다.
        redirectAttributes.addFlashAttribute("result", bno);

        // 사용자를 "/board/list" 경로로 리다이렉트하여 등록 결과를 표시합니다.
        return "redirect:/board/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model) {    // @GetMapping 어노테이션을 통해 "/read" 경로로 들어온 HTTP GET 요청을 처리하는 메서드입니다.
        // 'PageRequestDTO'는 페이지 요청 정보를 나타내는 객체로, 페이지 번호, 페이지 크기, 검색어 등의 정보를 포함합니다.
        // 'Model'은 데이터를 뷰로 전달하는 데 사용되는 Spring의 모델 객체입니다.
        // 'BoardDTO' 객체를 사용하여 게시물 정보를 가져옵니다. 'boardService.readOne(bno)'는 게시물 번호에 해당하는 게시물 정보


        BoardDTO boardDTO = boardService.readOne(bno);   // 'BoardDTO' 객체를 사용하여 게시물 정보를 가져옵니다. 'boardService.readOne(bno)'는 게시물 번호에 해당하는 게시물 정보를 데이터베이스에서 가져오는 서비스 메서드를 호출합니다.
        // 가져온 게시물 정보를 'boardDTO' 객체에 저장합니다.

        //log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
        // 'model.addAttribute("dto", boardDTO)'를 사용하여 'boardDTO' 객체를 뷰에 전달합니다.
        // 이를 통해 게시물 정보를 뷰(화면)에서 사용할 수 있게 됩니다.

    }
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO, @Valid BoardDTO boardDTO,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {     // 1. 폼 입력 유효성 검사를 수행합니다.


        if (bindingResult.hasErrors()) {// 2. 유효성 검사에서 오류가 발생한 경우 실행됩니다.
            String link = pageRequestDTO.getLink();     // 2.1. 페이지 요청 정보에서 링크를 가져옵니다.
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors()); // 2.2. 오류 메시지를 리다이렉트 속성에 추가합니다.
            redirectAttributes.addAttribute("bno", boardDTO.getBno());  // 2.3. 게시물 번호 (bno)를 리다이렉트 속성에 추가합니다.
            return "redirect:/board/modify?"+link;// 2.4. 오류가 발생한 경우 다시 수정 페이지로 리다이렉트합니다.
        }

        // 3. 폼 입력 유효성 검사를 통과한 경우 실행됩니다.

        // 3.1. boardService를 사용하여 게시물을 수정합니다.
        boardService.modify(boardDTO);

        // 3.2. 수정이 성공한 경우 리다이렉트 속성에 "result" 속성을 추가합니다.
        redirectAttributes.addFlashAttribute("result", "modified");

        // 3.3. 수정된 게시물의 번호 (bno)를 리다이렉트 속성에 추가합니다.
        redirectAttributes.addAttribute("bno", boardDTO.getBno());

        // 3.4. 수정이 완료되면 게시물 읽기 페이지로 리다이렉트합니다.
        return "redirect:/board/read";
    }
    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes) {   // 이 메서드는 게시물을 삭제하는 POST 요청을 처리하는 핸들러 메서드입니다.
                                                                                // bno 파라미터는 삭제할 게시물의 번호를 나타냅니다.
                                                                                // RedirectAttributes는 리다이렉트 시 플래시 애트리뷰트를 추가하는 데 사용됩니다.

        // 게시물 삭제 서비스를 호출하여 지정된 게시물을 삭제합니다.
        boardService.remove(bno);

        // 삭제가 완료되면 "result"라는 플래시 애트리뷰트에 "removed" 값을 추가하여
        // 삭제 결과를 리다이렉트 시에 전달합니다.

        redirectAttributes.addFlashAttribute("result", "removed");

        // 게시물이 삭제된 후, "/board/list" 경로로 리다이렉트합니다.
        return "redirect:/board/list";
    }


}
