package com.consultoriomedico.domain;

import java.util.ArrayList;
import java.util.List;


public class Agenda {
    private static final int MILISEGUNDOS_EN_UN_MINUTO = 60 * 1000;
    private List<Cita> citasPorFecha = new ArrayList<>();

    void agregarCita(Cita nuevaCita) {
        for (Cita cita : citasPorFecha) {
            long desde = cita.getFecha().getTime();
            long hasta = desde + cita.getDuracionMinutos() * MILISEGUNDOS_EN_UN_MINUTO;
            long desdeNueva = nuevaCita.getFecha().getTime();
            long hastaNueva = desdeNueva + nuevaCita.getDuracionMinutos() * MILISEGUNDOS_EN_UN_MINUTO;

            if (desde < hastaNueva && hasta > desdeNueva ) {
                throw new IllegalArgumentException("La agenda ya está ocupada en ese periodo de tiempo");
            }
        }

        citasPorFecha.add(nuevaCita);
    }
}
