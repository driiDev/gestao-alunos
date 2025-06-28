package com.soares.gestao_alunos.services;

import com.soares.gestao_alunos.infra.entities.Student;
import com.soares.gestao_alunos.infra.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void saveStudent(Student student){
        studentRepository.saveAndFlush(student);
    }

    public List<Student> findByFirstName(String firstName){
        return studentRepository.findByFirstNameIgnoreCase(firstName);
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
