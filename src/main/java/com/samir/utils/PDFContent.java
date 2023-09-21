package com.samir.utils;

import com.samir.dto.ChatGPTAnswerTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PDFContent {

    public static String returnContent(List<ChatGPTAnswerTemplate> chatGPTAnswerTemplates) {
        StringBuilder sb = new StringBuilder();

        AtomicInteger i= new AtomicInteger();
        chatGPTAnswerTemplates.forEach(template -> {
            i.getAndIncrement();
            sb.append(i.get());
            sb.append(")");
            sb.append(" ");
            sb.append(template.getQuestion());
            sb.append("\n");
            sb.append(template.getOptions());
            sb.append("\n");
            sb.append("\n");
        });

        return sb.toString();
    }
}
