package com.samir.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "interview_question")
@Data
public class InterviewQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "realm", length = 100, nullable = false)
    @NotEmpty
    private String realm;

    @Min(1)
    @Max(10)
    @Column(name = "from_target_exp", nullable = false)
    private Long fromTargetExp;

    @Min(1)
    @Max(10)
    @Column(name = "to_target_exp", nullable = false)
    private Long toTargetExp;

    @Min(0)
    @Max(10)
    @Column(name = "from_difficulty", nullable = false)
    private Long fromDifficulty;

    @Min(0)
    @Max(10)
    @Column(name = "to_difficulty", nullable = false)
    private Long toDifficulty;


    @Column(name = "question", length = 2000, nullable = false)
    @NotEmpty
    private String question;


    @Column(name = "answer", length = 1000, nullable = false)
    @NotEmpty
    private String answer;
}
