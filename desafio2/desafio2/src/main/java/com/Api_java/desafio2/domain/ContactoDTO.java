package com.Api_java.desafio2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ContactoDTO {
    public Integer id;
    public String nombre;
    public String celular;
    }


