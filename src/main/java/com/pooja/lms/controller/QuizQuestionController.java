package com.pooja.lms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pooja.lms.model.QuizQuestion;
import com.pooja.lms.service.QuizQuestionService;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "http://localhost:5500")
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;

    public QuizQuestionController(
            QuizQuestionService quizQuestionService) {

        this.quizQuestionService =
                quizQuestionService;
    }

    // Get all quiz questions
    @GetMapping
    public List<QuizQuestion> getAllQuestions() {

        return quizQuestionService
                .getAllQuestions();

    }

    // Get questions by course ID
    @GetMapping("/course/{courseId}")
    public List<QuizQuestion> getQuestionsByCourse(
            @PathVariable Long courseId) {

        return quizQuestionService
                .getQuestionsByCourse(courseId);

    }

    // Get question by ID
    @GetMapping("/{id}")
    public QuizQuestion getQuestionById(
            @PathVariable Long id) {

        return quizQuestionService
                .getQuestionById(id);

    }

    // Create a question
    @PostMapping
    public QuizQuestion createQuestion(
            @RequestBody QuizQuestion question) {

        return quizQuestionService
                .createQuestion(question);

    }

    // Delete a question
    @DeleteMapping("/{id}")
    public void deleteQuestion(
            @PathVariable Long id) {

        quizQuestionService
                .deleteQuestion(id);

    }

}