package com.example.quiz.controller;

import com.example.quiz.entities.QuizWrapper;
import com.example.quiz.entities.Response;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam Integer no, @RequestParam String title,@RequestParam String category){
        return quizService.createQuiz(no,title,category);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuizWrapper>>getQuiz(@PathVariable Integer id){
        return quizService.getQuiz(id);
    }
    @PostMapping("result/{id}")
    public ResponseEntity<Integer> resultQuiz(@PathVariable Integer id, @RequestBody List<Response> response ){
        return quizService.resultQuiz(id,response);
    }
}
