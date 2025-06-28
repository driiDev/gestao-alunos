package com.soares.gestao_alunos.services; // Mesmo pacote do StudentService

import com.soares.gestao_alunos.infra.entities.Course;
import com.soares.gestao_alunos.infra.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Curso não encontrado para exclusão.");
        }
        courseRepository.deleteById(id);
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