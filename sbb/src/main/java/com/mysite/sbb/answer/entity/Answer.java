package com.mysite.sbb.answer.entity;

import com.mysite.sbb.audit.BaseEntity;
import com.mysite.sbb.member.entity.Member;
import com.mysite.sbb.question.entity.Question;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
//@ToString(exclude = "")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@EntityListeners(AuditingEntityListener.class) // 이게 들어가야 db에 날짜가 생성됨
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id; // 아이디

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

//    @CreatedDate
//    private LocalDateTime created; // 작성일

    // 관계도
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member author;


}
