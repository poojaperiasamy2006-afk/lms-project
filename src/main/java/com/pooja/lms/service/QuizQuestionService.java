package com.pooja.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pooja.lms.model.QuizQuestion;
import com.pooja.lms.repository.QuizQuestionRepository;

@Service
public class QuizQuestionService {

    private final QuizQuestionRepository quizQuestionRepository;

    public QuizQuestionService(
            QuizQuestionRepository quizQuestionRepository) {

        this.quizQuestionRepository =
                quizQuestionRepository;
    }

    // Get all quiz questions
    public List<QuizQuestion> getAllQuestions() {

        return quizQuestionRepository.findAll();

    }

    // Get questions for a particular course
    public List<QuizQuestion> getQuestionsByCourse(
            Long courseId) {

        return quizQuestionRepository
                .findByCourseId(courseId);

    }

    // Get question by ID
    public QuizQuestion getQuestionById(Long id) {

        return quizQuestionRepository
                .findById(id)
                .orElse(null);

    }

    // Create question
    public QuizQuestion createQuestion(
            QuizQuestion question) {

        return quizQuestionRepository
                .save(question);

    }

    // Delete question
    public void deleteQuestion(Long id) {

        quizQuestionRepository
                .deleteById(id);

    }

}