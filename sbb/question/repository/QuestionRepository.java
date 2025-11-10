package com.mysite.sbb.question.repository;

import com.mysite.sbb.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // 제목(subject)을 기준으로 부분 일치 검색(LIKE) 수행 -> ex. %스프링%
    Optional<Question> findBySubjectLike(String query);

    // 페이징 기능
    Page<Question> findAll(Pageable pageable);
}
