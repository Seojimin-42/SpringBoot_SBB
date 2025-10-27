package com.mysite.sbb.member.controller;

import com.mysite.sbb.member.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/signup";
    }

}
