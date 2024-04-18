package com.samir.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
public class QuestionCreationRequest {

    @Min(1)
    @NotNull
    private Long numberOfQuestions;

    @NotEmpty
    private String questionType;

    @NotEmpty
    private String realm;

    @NotEmpty
    private String profession;

    @Min(1)
    @Max(10)
    @NotNull
    private Long fromTargetExp;

    @Min(1)
    @Max(10)
    @NotNull
    private Long toTargetExp;

    @Min(0)
    @Max(10)
    @NotNull
    private Long fromDifficulty;

    @Min(0)
    @Max(10)
    @NotNull
    private Long toDifficulty;
}
