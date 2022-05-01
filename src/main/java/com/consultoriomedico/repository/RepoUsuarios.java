package com.consultoriomedico.repository;

import com.consultoriomedico.domain.Doctor;
import com.consultoriomedico.domain.Paciente;
import com.consultoriomedico.domain.Persona;

import java.util.List;

public interface RepoUsuarios {

    void grabar(Object usuario);

    List<Doctor> listarDoctores();

    List<Paciente> listarPacientes();

    Persona buscarPorId(int id);

}
