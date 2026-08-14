package com.pooja.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pooja.lms.model.QuizQuestion;

public interface QuizQuestionRepository
        extends JpaRepository<QuizQuestion, Long> {

    List<QuizQuestion> findByCourseId(Long courseId);

}