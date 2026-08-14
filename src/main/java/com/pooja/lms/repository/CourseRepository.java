package com.pooja.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pooja.lms.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
