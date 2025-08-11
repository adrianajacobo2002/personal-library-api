package com.example.personal_library_api.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String genero;
    private String portada;
}
