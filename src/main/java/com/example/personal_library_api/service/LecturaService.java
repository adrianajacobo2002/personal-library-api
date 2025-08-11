package com.example.personal_library_api.service;

import com.example.personal_library_api.domain.model.Lectura;
import com.example.personal_library_api.service.dto.MarcarLecturaDTO;

import java.util.List;

public interface LecturaService {
    List<Lectura> listarPorUsuario(Long usuarioId);
    Lectura marcar(Long usuarioId, Long libroId, MarcarLecturaDTO dto);
}
