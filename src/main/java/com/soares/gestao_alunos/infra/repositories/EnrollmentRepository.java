package com.soares.gestao_alunos.infra.repositories;

import com.soares.gestao_alunos.infra.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
}
