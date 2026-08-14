package com.pooja.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pooja.lms.model.Enrollment;
import com.pooja.lms.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {

        Enrollment existingEnrollment =
                enrollmentRepository.findById(id).orElse(null);

        if (existingEnrollment != null) {
            existingEnrollment.setStudentId(enrollment.getStudentId());
            existingEnrollment.setCourseId(enrollment.getCourseId());
            existingEnrollment.setEnrollmentDate(
                    enrollment.getEnrollmentDate());
            existingEnrollment.setStatus(enrollment.getStatus());

            return enrollmentRepository.save(existingEnrollment);
        }

        return null;
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
