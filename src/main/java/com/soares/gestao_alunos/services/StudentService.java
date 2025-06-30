package com.soares.gestao_alunos.services;

import com.soares.gestao_alunos.infra.entities.CourseStatus;
import com.soares.gestao_alunos.infra.entities.Enrollment;
import com.soares.gestao_alunos.infra.entities.Student;
import com.soares.gestao_alunos.infra.repositories.CourseRepository;
import com.soares.gestao_alunos.infra.repositories.EnrollmentRepository;
import com.soares.gestao_alunos.infra.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public void saveStudent(Student student){
        studentRepository.saveAndFlush(student);
    }

    @Transactional
    public Enrollment enrollStudent(Integer studentId, Integer courseId){
        var student = studentRepository.findById(studentId).orElseThrow(()->
                new NoSuchElementException("Estudante não encontrado"));

        var course = courseRepository.findById(courseId).orElseThrow(()->
                new NoSuchElementException("Curso não encontrado"));

        var enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus(CourseStatus.IN_PROGRESS);

        return enrollmentRepository.save(enrollment);
    }

    public List<Student> findByFirstName(String firstName){
        return studentRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public void deleteStudent(Integer id){
        if (!studentRepository.existsById(id)){
            throw new RuntimeException("Aluno não encontrado para exclusão.");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudentById(Integer id, Student student){
        Student existingStudent = studentRepository.findById(id).orElseThrow(()->
                new RuntimeException("Aluno não encontrado"));

        existingStudent.setFirstName(student.getFirstName() != null
                ? student.getFirstName() : existingStudent.getFirstName());

        existingStudent.setLastName(student.getLastName() != null
                ? student.getLastName() : existingStudent.getLastName());

        existingStudent.setEmail(student.getEmail() != null
                ? student.getEmail() : existingStudent.getEmail());

        existingStudent.setDateBirth(student.getDateBirth() != null
                ? student.getDateBirth() : existingStudent.getDateBirth());

        existingStudent.setCpf(student.getCpf() != null
                ? student.getCpf() : existingStudent.getCpf());

        existingStudent.setGender(student.getGender() != null
                ? student.getGender() : existingStudent.getGender());
    }

}
