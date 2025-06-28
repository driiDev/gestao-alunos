package com.soares.gestao_alunos.infra.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "students")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @NotBlank(message = "O nome não pode estar vazio")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "O sobrenome não pode estar vazio")
    private String lastName;

    @Column(name = "data_birth")
    @NotNull
    private LocalDate dateBirth;

    @Column(name = "cpf", unique = true)
    @NotBlank
    private String cpf;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email", unique = true)
    @NotBlank(message = "O email não pode estar vazio")
    private String email;

    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDate registrationDate;

    @PrePersist
    public void prePersist(){
        if (registrationDate == null){
            registrationDate = LocalDate.now();
        }
    }

    @Valid
    @Embedded
    private Address address;

}
