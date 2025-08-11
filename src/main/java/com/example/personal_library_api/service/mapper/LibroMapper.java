package com.example.personal_library_api.service.mapper;

import com.example.personal_library_api.domain.model.Libro;
import com.example.personal_library_api.domain.model.Usuario;
import com.example.personal_library_api.service.dto.CrearLibroDTO;
import com.example.personal_library_api.service.dto.LibroDTO;

public final class LibroMapper {
    private LibroMapper() {}

    public static Libro toEntity(CrearLibroDTO d, Usuario u) {
        var l = new Libro();
        l.setTitulo(d.getTitulo());
        l.setAutor(d.getAutor());
        l.setGenero(d.getGenero());
        l.setPortada(d.getPortada());
        l.setUsuario(u);
        return l;
    }

    public static LibroDTO toDto(Libro l){
        return LibroDTO.builder()
                .id(l.getId())
                .titulo(l.getTitulo())
                .autor(l.getAutor())
                .genero(l.getGenero())
                .portada(l.getPortada())
                .build();
    }
}
