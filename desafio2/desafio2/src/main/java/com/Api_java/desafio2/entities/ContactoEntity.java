package com.Api_java.desafio2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name= "Personas")
@Data
@NoArgsConstructor
@AllArgsConstructor

    public class ContactoEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", columnDefinition = "INT UNSIGNED")
    public Integer id;

    @Column( name = "nombre", columnDefinition = "VARCHAR(50)")
    public String nombre;

    @Column( name = "celular", columnDefinition = "VARCHAR(50)")
    public String celular;

}
