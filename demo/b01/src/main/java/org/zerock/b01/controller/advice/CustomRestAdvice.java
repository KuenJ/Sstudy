package org.zerock.b01.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {

    // BindException을 처리하는 클래스입니다.
    @ExceptionHandler(BindException.class)
    // HTTP 응답 상태를 EXPECTATION_FAILED로 설정합니다.
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e) {
        // 로그를 남깁니다.
        log.error(e);

        // 에러 정보를 담을 맵을 생성합니다.
        Map<String, String> errorMap = new HashMap<>();

        // 만약 에러가 있다면
        if (e.hasErrors()) {
            // BindingResult를 얻습니다.
            BindingResult bindingResult = e.getBindingResult();

            // 각 필드 에러에 대한 정보를 맵에 추가합니다.
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getCode());
            });
        }
        // 클라이언트에게 Bad Request와 함께 에러 맵을 응답합니다.
        return ResponseEntity.badRequest().body(errorMap);
    }
}
