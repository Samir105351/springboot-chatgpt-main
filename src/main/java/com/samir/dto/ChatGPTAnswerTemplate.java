package com.samir.dto;

import lombok.Data;

@Data
public class ChatGPTAnswerTemplate {
    private String question;
    private Integer difficulty;
    private String options;
    private String answer;

    private String profession;
    private String questionType;
    private String realm;
}
