package com.soares.gestao_alunos.infra.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "courses")
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_name", unique = true)
    @NotBlank
    private String courseName;

    @Column(name = "description")
    private String description;

    @Column(name = "course_duration")
    @NotNull
    private double courseDuration;
}
