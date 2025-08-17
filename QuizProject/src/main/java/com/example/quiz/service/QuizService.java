package com.example.quiz.service;

import com.example.quiz.dao.QuestionDao;
import com.example.quiz.dao.QuizDao;
import com.example.quiz.entities.Question;
import com.example.quiz.entities.Quiz;
import com.example.quiz.entities.QuizWrapper;
import com.example.quiz.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(Integer no, String title, String category) {
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
       List<Question> questions= questionDao.createQuizRandomByNo(no,category);
       quiz.setQuestions(questions);
       quizDao.save(quiz);
       return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuizWrapper>> getQuiz(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questions=quiz.get().getQuestions();
        List<QuizWrapper> wrappers=new ArrayList<>();
        for(Question q:questions){
            QuizWrapper qw= new QuizWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            wrappers.add(qw);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> resultQuiz(Integer id, List<Response> response) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> ques=quiz.getQuestions();
        int count=0;
        int i=0;
        for(Response r:response){
            if( r.getResponse().equals(ques.get(i).getRightAnswer()))
                count++;
            i++;
        }
        return new ResponseEntity<>(count,HttpStatus.OK);

    }
}
