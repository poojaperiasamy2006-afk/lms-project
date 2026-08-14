package com.pooja.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pooja.lms.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
