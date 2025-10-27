package com.mysite.sbb.study.controller;

import com.mysite.sbb.question.dto.QuestionDto;
import com.mysite.sbb.question.service.QuestionService;
import com.mysite.sbb.study.dto.StudyDto;
import com.mysite.sbb.study.entity.Study;
import com.mysite.sbb.study.repository.StudyRepository;
import com.mysite.sbb.study.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/study") // 디폴트로 /study가 나옴(공통으로 쓰기 위함)
@RequiredArgsConstructor
@Slf4j // 로그 출력

public class StudyController {

    @Autowired // 스프링이 자동으로 객체를 넣어줌 ex. new
    private final StudyService studyService;
    private final StudyRepository studyRepository;

    @GetMapping("/list")
    String list(Model model){
        List<Study> result = studyRepository.findAll();
        model.addAttribute("result", result);
        return "study/list";
    }

    @GetMapping("/create") // 입력 폼을 띄우는 용도
    public String createStudy(StudyDto studyDto, Model model){
        model.addAttribute("studyDto", studyDto); // 전송할 데이터
        return "study/inputForm";
    }

    @PostMapping("/create") // 입력 값을 저장하는 용도
    public String saveQuestion(@Valid StudyDto studyDto, // @Valid -> 사용자가 입력한 데이터가 올바른지 자동으로 검사해주는 기능(검사 실행자)
                               BindingResult bindingResult){ // @BindingResult -> 검사 결과표

        if(bindingResult.hasErrors()){ // 에러가 존재하면
            return "study/inputForm"; // 다시 폼 페이지로 이동
        }

        studyService.create(studyDto); // 에러가 없으면 DB에 저장
        return "redirect:/study/list"; // 저장 후 목록 페이지로 이동
    }

}
