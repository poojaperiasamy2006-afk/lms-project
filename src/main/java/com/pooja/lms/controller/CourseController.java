package com.pooja.lms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pooja.lms.model.Course;
import com.pooja.lms.service.CourseService;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:5500")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Create Course
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // Get All Courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Get Course By ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {

        Course course = courseService.getCourseById(id);

        if (course != null) {
            return ResponseEntity.ok(course);
        }

        return ResponseEntity.notFound().build();
    }

    // Update Course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        Course updatedCourse = courseService.updateCourse(id, course);

        if (updatedCourse != null) {
            return ResponseEntity.ok(updatedCourse);
        }

        return ResponseEntity.notFound().build();
    }

    // Delete Course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}
