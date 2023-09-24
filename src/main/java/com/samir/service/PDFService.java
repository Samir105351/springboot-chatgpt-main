package com.samir.service;

import com.samir.dto.QuestionGetRequest;

public interface PDFService {
    public byte[] generatePDF(QuestionGetRequest request);
}
