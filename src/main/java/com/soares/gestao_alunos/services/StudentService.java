package com.soares.gestao_alunos.services;

import com.soares.gestao_alunos.infra.entities.Student;
import com.soares.gestao_alunos.infra.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Student student){
        studentRepository.saveAndFlush(student);
    }

    public List<Student> findByName(String name){
        return studentRepository.findByName(name);
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public void deleteStudents(Integer id){
        studentRepository.deleteById(id);
    }

    public void updateStudentById(Integer id, Student student){
        Student studentEntity = studentRepository.findById(id).orElseThrow(()->
                new RuntimeException("Aluno não encontrado"));

        studentEntity.setEmail(student.getEmail() != null
                ? student.getEmail() : studentEntity.getEmail());

    }
}
