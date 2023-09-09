package com.samir.Repository;

import com.samir.dto.InterviewQuestionCreationRequest;
import com.samir.entity.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Long> {
    @Query(value = "SELECT * " +
            "FROM ( " +
            "    SELECT iq.* " +
            "    FROM interview_question iq " +
            "    WHERE " +
            "        iq.realm = :#{#request.realm} " +
            "        AND iq.from_target_exp <= :#{#request.toTargetExp} " +
            "        AND iq.to_target_exp >= :#{#request.fromTargetExp} " +
            "        AND iq.from_difficulty <= :#{#request.toDifficulty} " +
            "        AND iq.to_difficulty >= :#{#request.fromDifficulty} " +
            "    ORDER BY DBMS_RANDOM.RANDOM " +
            ") " +
            "WHERE ROWNUM <= :#{#request.numberOfQuestions}", nativeQuery = true)
    List<InterviewQuestion> getRandomQuestions(@Param("request") InterviewQuestionCreationRequest request);
}

