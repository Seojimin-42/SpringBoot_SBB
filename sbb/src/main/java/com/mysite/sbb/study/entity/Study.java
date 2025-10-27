package com.mysite.sbb.study.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString // toString() 생성
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 받는 생성자 생성
@Builder
@EntityListeners(AuditingEntityListener.class) // 엔티티 생성·수정 시 자동으로 날짜를 기록(Auditing 기능 활성화)
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 아이디

    private String name; // 이름

    private String studentId; // 학번
    
    private String level; // 난이도

    private String interest; // 흥미

    private Boolean offlineAvailable; // 오프라인

    @CreatedDate
    private LocalDateTime created; // 작성일
}
