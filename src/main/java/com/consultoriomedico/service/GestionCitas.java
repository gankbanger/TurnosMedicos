package com.consultoriomedico.service;

import java.util.Date;
import java.util.List;

import com.consultoriomedico.domain.Cita;
import com.consultoriomedico.domain.Doctor;
import com.consultoriomedico.domain.Paciente;

public interface GestionCitas {

    void crearCita(Doctor doctor, Paciente paciente, Date fecha);
    List<Cita> obtenerCitas(Doctor doctor);
}
