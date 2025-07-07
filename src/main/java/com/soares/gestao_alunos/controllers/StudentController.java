package com.soares.gestao_alunos.controllers;

import com.soares.gestao_alunos.infra.entities.CourseStatus;
import com.soares.gestao_alunos.infra.entities.Enrollment;
import com.soares.gestao_alunos.infra.entities.Student;
import com.soares.gestao_alunos.infra.repositories.CourseRepository;
import com.soares.gestao_alunos.infra.repositories.EnrollmentRepository;
import com.soares.gestao_alunos.infra.repositories.StudentRepository;
import com.soares.gestao_alunos.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    @PostMapping
    public ResponseEntity<Void> saveStudent(@Valid @RequestBody Student student){
        studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Void> enrollStudent(@PathVariable Integer studentId, @PathVariable Integer courseId){
        studentService.enrollStudent(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Student>> findByFirstName(@Valid @RequestParam String firstName){
        return ResponseEntity.ok(studentService.findByFirstName(firstName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Student>> findAllStudents(){
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/{studentId}/enrollments")
    public ResponseEntity<List<Enrollment>> findStudentEnrollments(@PathVariable Integer studentId){
        var student = studentRepository.findById(studentId);

        if(student.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var enrollments = enrollmentRepository.findByStudentId(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStudentById(@Valid @PathVariable Integer id, @RequestBody Student student){
        studentService.updateStudentById(id, student);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{studentId}/enroll/{enrollmentId}/status")
        public ResponseEntity<Void> updateEnrollmentStatus(@Valid @PathVariable Integer studentId, @PathVariable Integer enrollmentId, @RequestParam CourseStatus newStatus){
            studentService.updateEnrollmentStatus(studentId, enrollmentId, newStatus);
            return ResponseEntity.ok().build();
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@Valid @PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
