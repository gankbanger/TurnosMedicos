package com.consultoriomedico.ui;

import java.util.Scanner;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MenuPrincipal extends Menu implements Pantalla {

    private Pantalla menuSeleccionDoctor;
    private Pantalla menuCreacionCita;

    public Pantalla mostrar() {
        System.out.println("1. Ver calendario por doctor");
        System.out.println("2. Crear cita");
        System.out.println("3. Salir");
        System.out.print(": ");

        int select = obtenerSeleccion(1, 3);
        switch (select) {
            case 1:
                return menuSeleccionDoctor;
            case 2:
                return menuCreacionCita;
            case 3:
                return null;
            default:
                throw new IllegalStateException("Selección invalida: " + select);
        }
    }
}
