package com.soares.gestao_alunos.services; // Mesmo pacote do StudentService

import com.soares.gestao_alunos.infra.entities.Course;
import com.soares.gestao_alunos.infra.repositories.CourseRepository;
import com.soares.gestao_alunos.infra.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public void saveCourse(Course course){
        courseRepository.save(course);
    }

    public Optional<Course> findCourseById(Integer id) {
        return courseRepository.findById(id);
    }

    public List<Course> findAllCourses(){
        return courseRepository.findAll();
    }

    public List<Course> findByCourseName(String courseName){
        return courseRepository.findByCourseNameContainingIgnoreCase(courseName);
    }

    @Transactional
    public void deleteCourse(Integer id){
        var course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado para exclusão."));

        var enrollments = enrollmentRepository.findByCourseId(course.getId());

        if (!enrollments.isEmpty()) {
            throw new RuntimeException("Não é possível excluir curso com matrícula(s) ativa(s).");
        }

        courseRepository.delete(course);
    }


    @Transactional
    public Course updateCourseById(Integer id, Course updatedCourseData){
        Course existingCourse = courseRepository.findById(id).orElseThrow(()->
                new RuntimeException("Curso não encontrado para atualização."));

        existingCourse.setCourseName(updatedCourseData.getCourseName() != null
                ? updatedCourseData.getCourseName() : existingCourse.getCourseName());

        existingCourse.setDescription(updatedCourseData.getDescription() != null
                ? updatedCourseData.getDescription() : existingCourse.getDescription());

        existingCourse.setCourseDuration(updatedCourseData.getCourseDuration() != 0.0
                ? updatedCourseData.getCourseDuration() : existingCourse.getCourseDuration());

        return courseRepository.save(existingCourse);
    }
}