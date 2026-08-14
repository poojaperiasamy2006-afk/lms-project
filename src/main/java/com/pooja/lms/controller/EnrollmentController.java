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

import com.pooja.lms.model.Enrollment;
import com.pooja.lms.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "http://localhost:5500")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // Create Enrollment
    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.createEnrollment(enrollment);
    }

    // Get All Enrollments
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // Get Enrollment By ID
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {

        Enrollment enrollment = enrollmentService.getEnrollmentById(id);

        if (enrollment != null) {
            return ResponseEntity.ok(enrollment);
        }

        return ResponseEntity.notFound().build();
    }

    // Update Enrollment
    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(
            @PathVariable Long id,
            @RequestBody Enrollment enrollment) {

        Enrollment updatedEnrollment =
                enrollmentService.updateEnrollment(id, enrollment);

        if (updatedEnrollment != null) {
            return ResponseEntity.ok(updatedEnrollment);
        }

        return ResponseEntity.notFound().build();
    }

    // Delete Enrollment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return ResponseEntity.noContent().build();
    }
}