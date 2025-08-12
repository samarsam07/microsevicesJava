package com.samar.question_service.dao;

import com.samar.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);


    @Query("SELECT q.id FROM Question q WHERE q.category = :categoryName")
    List<Integer> findRandomQuestionsByCategory(@Param("categoryName") String categoryName);
}
