package com.soares.gestao_alunos.infra.repositories;

import com.soares.gestao_alunos.infra.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByCourseNameContainingIgnoreCase(String courseName);
}
