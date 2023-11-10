package org.zerock.b01.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.b01.dto.ReplyDTO;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2

public class ReplyController {

    // 댓글을 등록하는 메서드
    // HTTP POST 요청을 통해 JSON 형식의 데이터를 받아서 댓글을 등록하고 결과를 반환합니다.

    @ApiOperation(value = "Replies POST",notes = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)

    public Map<String,Long> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult)throws BindException {
        log.info(replyDTO);


        if (bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        // 댓글을 등록하는 메서드
        // HTTP POST 요청을 통해 JSON 형식의 데이터를 받아서 댓글을 등록하고 결과를 반환합니다.

        // 로깅을 통해 이 메서드의 동작을 추적할 수 있습니다.
        // log.info(replyDTO);

        // 등록된 댓글의 정보를 담은 결과 맵을 생성합니다.


        Map<String,Long> resultMap = new HashMap<>();
        resultMap.put("rno",111L);

        // 결과 맵을 HTTP 응답으로 반환합니다.
        return  resultMap;
    }
}
