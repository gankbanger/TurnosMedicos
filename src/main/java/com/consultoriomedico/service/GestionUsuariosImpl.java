package com.consultoriomedico.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.consultoriomedico.domain.*;
import com.consultoriomedico.repository.RepoUsuarios;
import com.consultoriomedico.repository.RepoUsuariosImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.log4j.Logger;


@AllArgsConstructor
public class GestionUsuariosImpl implements GestionUsuarios {
    public static final Logger log = Logger.getLogger(GestionUsuariosImpl.class);

    private RepoUsuarios repoUsuarios;
    public List<Doctor> listarDoctores() {
        return repoUsuarios.listarDoctores();
    }

    public List<Paciente> buscarPaciente(String email, String telefono) {
        List<Paciente> pacientes = repoUsuarios.listarPacientes();
        List<Paciente> encontrados = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            if (paciente.getTelefono().equals(telefono) || paciente.getEmail().equals(email)) {
                encontrados.add(paciente);
            }
        }
        return encontrados;
    }

    public void crearUsuario() {
        log.info("[GestionUsuariosImpl][crearUsuario] Inicio de llamada creación usuario");
        try {
            pedirDatos();
        } catch (Exception ex) {
            log.error(ex.toString());
        }
    }

    public void pedirDatos() {
        Scanner sc = new Scanner(System.in);
        String especialidad;
        try {
            System.out.print("Se comenzará con la creación de usuario\nPor favor escriba el nombre del usuario: ");
            String nombreUsuario = sc.nextLine();
            System.out.print("Digite su documento de identidad: ");
            int id = sc.nextInt();
            if (RepoUsuariosImpl.builder().build().buscarPorId(id) == null) {
                sc.nextLine();
                boolean[] flagDoctorArr = validarDoctor();
                if (flagDoctorArr[1]) {
                    System.out.print("Escriba la dirección del usuario: ");
                    String direccion = sc.nextLine();
                    System.out.print("Escriba el telefono del usuario con la extensión de su país: ");
                    String telefono = sc.nextLine();
                    System.out.print("Escriba el email del usuario: ");
                    String email = sc.nextLine();

                    if (flagDoctorArr[0]) {
                        System.out.println("Escriba la especialidad del doctor: ");
                        especialidad = sc.nextLine();
                        Doctor doctor = Doctor.builder().id(id)
                                .creadoEn(new Date())
                                .especialidad(especialidad)
                                .flagDoctor(true)
                                .nombre(nombreUsuario)
                                .direccion(direccion)
                                .telefono(telefono)
                                .email(email).build();
                        log.info("[GestionUsuariosImpl][pedirDatos] -> " + doctor.toString());
                        RepoUsuariosImpl.builder().build().grabar(doctor);
                    } else {
                        Paciente paciente = Paciente.builder().id(id)
                                .creadoEn(new Date())
                                .flagDoctor(false)
                                .nombre(nombreUsuario)
                                .direccion(direccion)
                                .telefono(telefono)
                                .email(email)
                                .build();
                        RepoUsuariosImpl.builder().build().grabar(paciente);
                        log.info("[GestionUsuariosImpl][pedirDatos] -> " + paciente.toString());
                    }
                }
            } else {
                System.out.println("Ya existe un usuario con ese Documento de identidad, terminando programa");
            }
        } catch (Exception ex) {
            log.error(ex.toString());
        }
    }

    public boolean[] validarDoctor() {
        Scanner sc = new Scanner(System.in);
        int iterDoctorAnswer = 0;
        boolean flagDoctor = false;

        while (iterDoctorAnswer < 3) {
            System.out.print("El usuario es Paciente(P) o Doctor(D) elija una de las opciones (P/D): ");
            String flagDoctorString = sc.nextLine();
            if (Objects.equals(flagDoctorString, "D") || Objects.equals(flagDoctorString, "P")) {
                flagDoctor = Objects.equals(flagDoctorString, "D");
                break;
            } else {
                iterDoctorAnswer++;
                System.out.print("Opción inválida por favor digita P o D según corresponda\n");
            }
        }
        if (iterDoctorAnswer >= 3) System.out.println("Opción invalida reiterada, finalizando programa...");
        return new boolean[]{flagDoctor, iterDoctorAnswer < 3};
    }

    public void listarUsuarios() {
        log.info("[GestionUsuariosImpl][listarUsuarios] Se listarán los usuarios y pacientes");
        ArrayList<Doctor> listaDoctores = (ArrayList<Doctor>) RepoUsuariosImpl.builder().build().listarDoctores();
        ArrayList<Paciente> listaPacientes = (ArrayList<Paciente>) RepoUsuariosImpl.builder().build().listarPacientes();
        System.out.println("Lista de doctores");
        for (Doctor doctor : listaDoctores) {
            System.out.println(doctor.toString());
        }
        System.out.println("\nLista de pacientes: ");
        for (Paciente paciente : listaPacientes) {
            System.out.println(paciente.toString());
        }
    }


    public void buscarUsuarioPorId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor introduce el id a buscar: ");
        int id = sc.nextInt();
        log.info(String.format("[GestionUsuariosImpl][buscarUsuarioPorId] Buscando usuario por ID: %s", id));
        Persona usuario = RepoUsuariosImpl.builder().build().buscarPorId(id);
        if (usuario != null) {
            System.out.println("Se encontró el siguiente Usuario con ese id: \n" + usuario);
        } else {
            System.out.printf("No se encontró ningún usuario con el id: %s", id);
        }
    }


}
