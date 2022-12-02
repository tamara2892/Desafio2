package com.Api_java.desafio2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MensajeDTO {
    private Integer id;
    private String mensaje = "Añadido Exitosamente";

    public MensajeDTO(Integer id)
    { this.id = id; }

}
