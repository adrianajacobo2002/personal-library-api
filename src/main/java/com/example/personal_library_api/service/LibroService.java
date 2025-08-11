package com.example.personal_library_api.service;

import com.example.personal_library_api.service.dto.CrearLibroDTO;
import com.example.personal_library_api.service.dto.LibroDTO;

import java.util.List;

public interface LibroService {
    List<LibroDTO> listar(Long usuarioId);
    LibroDTO crear(Long usuarioId, CrearLibroDTO dto);
    void eliminar(Long usuarioId, Long libroId);
}
