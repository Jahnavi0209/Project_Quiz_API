package com.example.quiz.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "question")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String category;
    private String option1;
    private String questionTitle;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultylevel;



}
