package com.consultoriomedico.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class Doctor extends Persona {
    @NonNull
    private String especialidad;
    private Agenda agenda;
}