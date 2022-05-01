package com.consultoriomedico.ui;

import com.consultoriomedico.domain.Cita;
import com.consultoriomedico.domain.Doctor;
import com.consultoriomedico.service.GestionCitas;
import com.consultoriomedico.service.GestionUsuarios;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MenuSeleccionDoctor extends Menu implements Pantalla{

    GestionUsuarios getionUsuarios;
    GestionCitas gestionCitas;

    @Override
    public Pantalla mostrar() {
        var doctores = getionUsuarios.listarDoctores();

        int i = 1;
        for (Doctor doctor : doctores) {
            System.out.println(i + ". " + doctor.getNombre() + " - " + doctor.getEspecialidad());
            i++;
        }
        if (doctores.size() > 0) {
            int select = obtenerSeleccion(1, doctores.size());
            var citas = gestionCitas.obtenerCitas(doctores.get(select - 1));
            for (Cita cita : citas) {
                System.out.println(cita.getFecha());                
            }
        }
        return null;
    }
    
}
