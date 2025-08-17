package com.example.quiz.dao;

import com.example.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
   public List<Question> findByCategory(String category);

   @Query(value = "SELECT * FROM QUESTION q WHERE q.category=:category ORDER BY RANDOM() LIMIT :no",nativeQuery = true)
   List<Question> createQuizRandomByNo(Integer no, String category);
}
