package com.samir.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "interview_question")
@Data
public class InterviewQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "realm", length = 100, nullable = false)
    private String realm;


    @Column(name = "from_target_exp", nullable = false)
    private Long fromTargetExp;


    @Column(name = "to_target_exp", nullable = false)
    private Long toTargetExp;


    @Column(name = "from_difficulty", nullable = false)
    private Long fromDifficulty;


    @Column(name = "to_difficulty", nullable = false)
    private Long toDifficulty;


    @Column(name = "question", length = 2000, nullable = false)
    private String question;


    @Column(name = "answer", length = 1000, nullable = false)
    private String answer;
}
