package com.samir.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    private Long numberOfQuestions;
    private String realm;
    private Long fromTargetExp;
    private Long toTargetExp;
    private Long fromDifficulty;
    private Long toDifficulty;
}
