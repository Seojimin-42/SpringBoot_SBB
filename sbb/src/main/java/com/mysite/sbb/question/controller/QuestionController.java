package com.mysite.sbb.question.controller;

import com.mysite.sbb.answer.dto.AnswerDto;
import com.mysite.sbb.member.entity.Member;
import com.mysite.sbb.member.service.MemberService;
import com.mysite.sbb.question.dto.QuestionDto;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/question") // 디폴트로 /question이 나옴(공통으로 쓰기 위함)
@RequiredArgsConstructor
@Slf4j // 로그 출력
public class QuestionController {

        @Autowired // 스프링이 자동으로 객체를 넣어줌 ex. new
        private final QuestionService questionService;

        private final MemberService memberService;

        // 페이징 코드
        @GetMapping("/list")
        public String list(Model model,
                           @RequestParam(value = "page", defaultValue = "0") int page
                           ){
            Page<Question> paging = questionService.getList(page);
            log.info("=========== paging={}", paging);
            model.addAttribute("paging", paging);
            return "question/list";
        }

        @GetMapping("/detail/{id}")
        public String detail(@PathVariable("id") Long id, Model model,
                             AnswerDto answerDto){
            Question question = questionService.getQuestion(id);
            model.addAttribute("question", question);
            model.addAttribute("answerDto", answerDto);
            return "question/detail";
        }

        @GetMapping("/create") // 입력 폼을 띄우는 용도
        public String createQuestion(QuestionDto questionDto, Model model){
            model.addAttribute("questionDto", questionDto); // 전송할 데이터
            return "question/inputForm";
        }

        @PreAuthorize("isAuthenticated()") // 로그인한 사용자만 접근 가능
        @PostMapping("/create") // 입력 값을 저장하는 용도
        public String saveQuestion(@Valid QuestionDto questionDto, // @Valid -> 사용자가 입력한 데이터가 올바른지 자동으로 검사해주는 기능(검사 실행자)
                                   BindingResult bindingResult,  // @BindingResult -> 검사 결과표
                                   Principal principal){

            if(bindingResult.hasErrors()){ // 에러가 존재하면
                return "question/inputForm"; // 다시 폼 페이지로 이동
            }

            log.info("=== principal : " + principal.getName());
            Member member = memberService.getMember(principal.getName());

            questionService.create(questionDto, member); // 에러가 없으면 DB에 저장
            return "redirect:/question/list"; // 저장 후 목록 페이지로 이동
        }
    }
