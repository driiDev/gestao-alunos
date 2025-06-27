package com.soares.gestao_alunos.infra.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "alunos")
@Entity
public class Alunos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private Date dateBirth;

    private String cpf;

    private String gender;

    private String email;

    @Embeddable
    private Endereco endereco();

}
