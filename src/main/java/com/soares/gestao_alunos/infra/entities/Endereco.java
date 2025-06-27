package com.soares.gestao_alunos.infra.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Endereco {

    private String cep;

    private String country;

    private String state;

    private String city;

    private String district;

    private String street;

    private String numberHouse;

    private String complement;

}
