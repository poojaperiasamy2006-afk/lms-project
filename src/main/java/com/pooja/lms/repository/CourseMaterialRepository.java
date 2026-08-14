package com.pooja.lms.repository;

import com.pooja.lms.model.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
}