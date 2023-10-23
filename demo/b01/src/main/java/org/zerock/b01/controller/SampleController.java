package org.zerock.b01.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
            public  void hello(Model model){
        log.info("hello...............");


        model.addAttribute("msg","Hello world");
    }
    @GetMapping("/ex/ex1")
    public  void ex1(Model model){
        List<String> list = Arrays.asList("AAA","BBB","CCC","DDD");
        model.addAttribute("list",list);
        //Arrays.asList는  배열을  리스트로 변환하게 해줄때 편리하게 사용가능한 코드

    }

}
