package com.samir.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "interview_question")
@Data
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question-type", length = 100, nullable = false)
    @NotEmpty
    private String questionType;


    @Column(name = "realm", length = 100, nullable = false)
    @NotEmpty
    private String realm;

    @Column(name = "profession", length = 100, nullable = false)
    @NotEmpty
    private String profession;

    @Min(1)
    @Max(10)
    @Column(name = "difficulty", nullable = false)
    private Long difficulty;

    @Column(name = "question", length = 2000, nullable = false)
    @NotEmpty
    private String question;

    @Column(name = "answer", length = 2000, nullable = false)
    @NotEmpty
    private String answer;

    @Column(name = "options", length = 2000, nullable = true)
    @NotEmpty
    private String options;
}
