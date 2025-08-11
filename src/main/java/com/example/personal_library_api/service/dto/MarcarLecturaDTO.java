package com.example.personal_library_api.service.dto;

import com.example.personal_library_api.domain.model.Lectura;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MarcarLecturaDTO {
    private Lectura.Estado estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String comentario;
}
