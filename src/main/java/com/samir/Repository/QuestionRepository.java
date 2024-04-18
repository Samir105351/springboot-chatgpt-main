package com.samir.Repository;

import com.samir.dto.QuestionCreationRequest;
import com.samir.dto.QuestionGetRequest;
import com.samir.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM interview_question " +
            "WHERE question_type = :#{#request.questionType} " +
            "AND realm = :#{#request.realm} " +
            "AND profession = :#{#request.profession} " +
            "AND difficulty BETWEEN :#{#request.fromDifficulty} AND :#{#request.toDifficulty} " +
            "ORDER BY DBMS_RANDOM.VALUE " +
            "FETCH FIRST :#{#request.numberOfQuestions} ROWS ONLY", nativeQuery = true)
    List<Question> selectRandomQuestions(QuestionGetRequest request);
}

