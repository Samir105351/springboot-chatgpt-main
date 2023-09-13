package com.samir.utils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.samir.dto.InterviewQuestionCreationRequest;

public class JsonTemplate {
    public static ObjectNode createQuestionTemplate(InterviewQuestionCreationRequest request) {
        ObjectNode template = JsonNodeFactory.instance.objectNode();
        template.put("realm", request.getRealm());
        template.put("fromTargetExp", request.getFromTargetExp());
        template.put("toTargetExp", request.getToTargetExp());
        template.put("fromDifficulty", request.getFromDifficulty());
        template.put("toDifficulty", request.getToDifficulty());
        return template;
    }
}
