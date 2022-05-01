package com.consultoriomedico.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Cita extends Entidad {

    private Paciente paciente;
    private Doctor doctor;
    private Date fecha;
    private int duracionMinutos;

}
