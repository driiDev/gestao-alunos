package com.soares.gestao_alunos.controllers;


import com.soares.gestao_alunos.infra.entities.Course;
import com.soares.gestao_alunos.infra.repositories.CourseRepository;
import com.soares.gestao_alunos.services.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<Void> saveCourse(@Valid @RequestBody Course course){
        courseService.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Course>> findAllCourses(){
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Course>> findCourseById(@PathVariable Integer id){
        return ResponseEntity.ok(courseService.findCourseById(id));
    }

    @GetMapping
    public ResponseEntity<List<Course>> findCourseByName(@RequestParam String courseName){
        return ResponseEntity.ok(courseService.findByCourseName(courseName));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCourseById(@Valid @PathVariable Integer id, @RequestBody Course course){
        courseService.updateCourseById(id,course);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseById(@Valid @PathVariable Integer id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
