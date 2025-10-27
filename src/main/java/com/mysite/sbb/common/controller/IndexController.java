package com.mysite.sbb.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping("/temp/abc")
    public String abc(){

        return "temp/abc";
    }
}
