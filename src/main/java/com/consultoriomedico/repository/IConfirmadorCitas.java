package com.consultoriomedico.repository;

import com.consultoriomedico.domain.Cita;
import com.consultoriomedico.domain.Persona;

public interface IConfirmadorCitas {

    boolean enviarConfirmacion(Persona usuario, Cita cita);
}
