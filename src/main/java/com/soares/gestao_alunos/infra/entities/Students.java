package com.soares.gestao_alunos.infra.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "students")
@Entity
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstName")
    @NotBlank(message = "O nome não pode estar vazio")
    private String firstName;

    @Column(name = "lastName")
    @NotBlank(message = "O sobrenome não pode estar vazio")
    private String lastName;

    @Column(name = "dataBirth")
    private Date dateBirth;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    @NotBlank(message = "O email não pode estar vazio")
    private String email;

    @Embedded
    private Address address;

}
