package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @Autowired
    private QuizService quizService;

    //add question
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question){
        return ResponseEntity.ok(this.service.addQuestion(question));

    }

    //update the question
    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question){
        return ResponseEntity.ok(this.service.updateQuestion(question));

    }

    //get all the question of quid
    @GetMapping("/quiz/{qId}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qId") Long qId){
//        Quiz quiz = new Quiz();
//        quiz.setqId(qId);
//        Set<Question> questionsOfQuiz = this.service.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);

        Quiz quiz =this.quizService.getQuiz(qId);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList<>(questions);
        if(list.size() >Integer.parseInt(quiz.getNumberOfQuestions())){
            list = list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));

        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }


    //get single question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long quesId){
        return this.service.getQuestion(quesId);
    }


    //delete question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId){
    this.service.deleteQuestion(quesId);
    }
}
