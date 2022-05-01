package com.consultoriomedico.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
public class Persona extends Entidad {
    private String titulo;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;

}
