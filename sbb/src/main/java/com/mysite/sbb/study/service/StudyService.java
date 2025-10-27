package com.mysite.sbb.study.service;

import com.mysite.sbb.question.dto.QuestionDto;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.study.dto.StudyDto;
import com.mysite.sbb.study.entity.Study;
import com.mysite.sbb.study.repository.StudyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j // 로그 출력
public class StudyService {

    private final StudyRepository studyRepository;

    public Study getQuestion(Long id) {
        Study q = studyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 질문이 X"));
        return q;
    }

    public void create(StudyDto studyDto) {
        // DTO -> Entity 변환
        Study study = Study.builder()
                .name(studyDto.getName())
                .studentId(studyDto.getStudentId())
                .level(studyDto.getLevel())
                .interest(studyDto.getInterest())
                .offlineAvailable(studyDto.getOfflineAvailable())
                .build();
        studyRepository.save(study);
    }
}
