package com.samir.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
public class InterviewQuestionCreationRequest {

    @Min(1)
    @NotNull
    private Long numberOfQuestions;

    @NotEmpty
    private String realm;

    @Min(1)
    @NotNull
    private Long fromTargetExp;

    @Max(10)
    @NotNull
    private Long toTargetExp;

    @Min(0)
    @NotNull
    private Long fromDifficulty;
    @Max(10)
    @NotNull
    private Long toDifficulty;
}
