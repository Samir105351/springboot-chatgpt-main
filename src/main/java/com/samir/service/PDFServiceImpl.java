package com.samir.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.samir.dto.ChatGPTAnswerTemplate;
import com.samir.entity.Question;
import com.samir.utils.PDFContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PDFServiceImpl implements PDFService {
    private final DatabaseService databaseService;

    @Override
    public byte[] generatePDF() {
        List<Question> questionList= databaseService.getAllInterviewQuestions();
        List<ChatGPTAnswerTemplate> templates=
        questionList.stream()
                .map(question -> {
                    ChatGPTAnswerTemplate template = new ChatGPTAnswerTemplate();
                    template.setQuestion(question.getQuestion());
                    template.setDifficulty(question.getDifficulty().intValue());
                    template.setOptions(question.getOptions());
                    template.setAnswer(question.getAnswer());
                    template.setProfession(question.getProfession());
                    template.setQuestionType(question.getQuestionType());
                    template.setRealm(question.getRealm());
                    return template;
                })
                .toList();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, byteArrayOutputStream);

            document.open();

            String paraText = PDFContent.returnContent(templates);
            Paragraph paragraph = new Paragraph(paraText);

            document.add(paragraph);

            document.close();
            pdfWriter.close();

            return byteArrayOutputStream.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
