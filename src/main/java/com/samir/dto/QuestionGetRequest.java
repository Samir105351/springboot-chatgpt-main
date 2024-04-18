package com.samir.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionGetRequest {
    private Long numberOfQuestions;

    private String questionType;

    private String realm;

    private String profession;



    private Long fromDifficulty;


    private Long toDifficulty;
}
