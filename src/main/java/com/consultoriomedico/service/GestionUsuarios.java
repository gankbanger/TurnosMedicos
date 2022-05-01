package com.consultoriomedico.service;

import java.util.List;

import com.consultoriomedico.domain.Doctor;
import com.consultoriomedico.domain.Paciente;
import com.consultoriomedico.domain.Persona;

public interface GestionUsuarios {
    
    List<Doctor> listarDoctores();
    List<Paciente> buscarPaciente(String telefono, String email);

}
