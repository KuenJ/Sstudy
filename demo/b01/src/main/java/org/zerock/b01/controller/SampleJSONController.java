package org.zerock.b01.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Log4j2

public class SampleJSONController {

    @GetMapping("/helloArr")
    public  String[] helloArr(){
        log.info("hello Arr......");

        return new String[]{"AAA","BBB","CCC"};
    }

}
