package com.example.personal_library_api.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearLibroDTO {
    @NotBlank @Size(max = 200)
    private String titulo;

    @NotBlank @Size(max = 100)
    private String autor;

    @Size(max = 50)
    private String genero;

    private String portada;
}
