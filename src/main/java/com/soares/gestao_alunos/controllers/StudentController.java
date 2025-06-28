package com.soares.gestao_alunos.controllers;

import com.soares.gestao_alunos.infra.entities.Student;
import com.soares.gestao_alunos.infra.repositories.StudentRepository;
import com.soares.gestao_alunos.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

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

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStudentById(@Valid @PathVariable Integer id, @RequestBody Student student){
        studentService.updateStudentById(id, student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@Valid @PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
