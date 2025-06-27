package com.soares.gestao_alunos.infra.repositories;

import com.soares.gestao_alunos.infra.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Integer> {

}
