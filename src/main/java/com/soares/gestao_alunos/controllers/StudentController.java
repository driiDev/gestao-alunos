package com.soares.gestao_alunos.controllers;

import com.soares.gestao_alunos.infra.entities.Student;
import com.soares.gestao_alunos.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> saveStudent(@Valid @RequestBody Student student){
        studentService.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Student>> findByFirstName(@Valid @RequestParam String firstName){
        return ResponseEntity.ok(studentService.findByFirstName(firstName));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Student>> findAllStudents(){
        return ResponseEntity.ok(studentService.findAllStudents());
    }

}
