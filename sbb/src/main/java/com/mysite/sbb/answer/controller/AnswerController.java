package com.mysite.sbb.answer.controller;

import com.mysite.sbb.answer.dto.AnswerDto;
import com.mysite.sbb.answer.service.AnswerService;
import com.mysite.sbb.member.entity.Member;
import com.mysite.sbb.member.service.MemberService;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()") // 로그인한 사용자만 접근 가능
    @PostMapping("/create/{id}")
    // 매개변수가 많아지면 dto를 쓴다.(2개 이상) questionController 참고
    public String create(@PathVariable("id") Long id,
                         @Valid AnswerDto answerDto,
                         BindingResult bindingResult,
                         Principal principal, // 현재 로그인된 사용자
                         Model model){

        Question question = questionService.getQuestion(id);

        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            model.addAttribute("answerDto", answerDto);
            return "question/detail";
        }

        Member member = memberService.getMember(principal.getName());

        answerService.create(question, answerDto, member);

        return "redirect:/question/detail/" + id;
    }
}
