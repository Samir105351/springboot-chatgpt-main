package com.samir.controller;

import com.samir.entity.Question;
import com.samir.service.DatabaseService;
import com.samir.service.PDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PDFController {

    private final PDFService pdfService;

    private final DatabaseService databaseService;

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePDF() {
        List<Question> questionList=databaseService.getAllInterviewQuestions();

        byte[] pdfBytes = pdfService.generatePDF();

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "hello_world.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(pdfBytes.length)
                    .body(pdfBytes);
        } else {
            return ResponseEntity.status(500).body(null);
        }
    }
}
