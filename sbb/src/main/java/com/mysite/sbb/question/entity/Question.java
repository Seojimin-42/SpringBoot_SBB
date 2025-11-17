package com.mysite.sbb.question.entity;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.audit.BaseEntity;
import com.mysite.sbb.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString // toString() 생성
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 받는 생성자 생성
@Builder
//@EntityListeners(AuditingEntityListener.class) // 엔티티 생성·수정 시 자동으로 날짜를 기록(Auditing 기능 활성화)
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id; // 아이디

    @Column(length = 200, nullable = false)
    private String subject; // 주제

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

//    @CreatedDate
//    private LocalDateTime created; // 작성일


    // 관계도
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member author;
}
