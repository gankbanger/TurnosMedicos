package com.consultoriomedico.ui;

import java.util.Scanner;

public abstract class Menu {
    protected int obtenerSeleccion(int min, int max) {
        int select = 0;
        try (var sc = new Scanner(System.in)) {            
            while (select < min && select > max) {
                System.out.print(": ");
                select = sc.nextInt();
            }
        }
        return select;
    }
}
