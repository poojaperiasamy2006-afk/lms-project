package com.pooja.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pooja.lms.model.Course;
import com.pooja.lms.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id).orElse(null);

        if (existingCourse != null) {
            existingCourse.setTitle(course.getTitle());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setTeacherId(course.getTeacherId());

            return courseRepository.save(existingCourse);
        }

        return null;
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}