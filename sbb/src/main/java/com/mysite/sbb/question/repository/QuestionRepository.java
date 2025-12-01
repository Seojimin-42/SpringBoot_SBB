package com.mysite.sbb.question.repository;

import com.mysite.sbb.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // 제목(subject)을 기준으로 부분 일치 검색(LIKE) 수행 -> ex. %스프링%
    Optional<Question> findBySubjectLike(String query);

    // 페이징 기능
    Page<Question> findAll(Pageable pageable);

    Page<Question> findAll(Specification<Question> specification, Pageable pageable);

    @Query(
            "SELECT distinct q "
            + "FROM Question q "
            + "LEFT OUTER JOIN Member m1 ON q.author = m1 "
            + "LEFT OUTER JOIN Answer a ON a.question = q "
            + "LEFT OUTER JOIN Member m2 ON a.author = m2 "
            + "WHERE q.subject LIKE %:keyword% "
            + "OR q.content LIKE %:keyword% "
            + "OR m1.username LIKE %:keyword% "
            + "OR a.content LIKE %:keyword% "
            + "OR m2.username LIKE %:keyword% "
    )
    Page<Question> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
