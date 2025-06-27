package com.soares.gestao_alunos.infra.repositories;

import com.soares.gestao_alunos.infra.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses, Integer> {

}
