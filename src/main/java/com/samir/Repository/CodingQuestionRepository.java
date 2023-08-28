package com.samir.Repository;

import com.samir.entity.CodingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodingQuestionRepository extends JpaRepository<CodingQuestion,Long> {
}
