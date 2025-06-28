package com.soares.gestao_alunos.infra.repositories;

import com.soares.gestao_alunos.infra.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student>findByFirstNameContainingIgnoreCase(String firstName);

}
