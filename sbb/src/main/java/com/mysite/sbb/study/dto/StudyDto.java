package com.mysite.sbb.study.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudyDto {

    @NotEmpty(message = "이름은 필수 항목입니다.")
    @Size(min=2, max = 20, message = "이름은 2~20자로 입력하세요.")
    private String name;

    @NotEmpty(message = "학번은 필수 항목입니다.")
    @Size(min=6, max = 12, message = "학번은 6~12자로 입력하세요.")
    private String studentId;

    @NotEmpty(message = "난이도를 선택하세요.")
    private String level;

    @NotEmpty(message = "관심 분야를 선택하세요.")
    private String interest;

    private Boolean offlineAvailable;

}
