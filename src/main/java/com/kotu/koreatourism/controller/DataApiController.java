package com.kotu.koreatourism.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/main")
public class DataApiController {

    @GetMapping()
    public String mainPage() {

        return "test성공";
    }

}
