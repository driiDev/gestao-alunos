package com.soares.gestao_alunos.infra.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {

    @Column(name = "cep")
    @NotBlank(message = "O cep não pode estar vazio")
    private String cep;

    @Column(name = "country")
    @NotBlank(message = "O país não pode estar vazio")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "street")
    private String street;

    @Column(name = "number_house")
    @NotBlank(message = "O numero não pode estar vazio")
    private String numberHouse;

    @Column(name = "complement")
    private String complement;

}
