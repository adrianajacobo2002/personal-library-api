package com.example.personal_library_api.service;

import com.example.personal_library_api.domain.model.Favorito;

import java.util.List;

public interface FavoritoService {
    List<Favorito> listar(Long usuarioId);
    void marcar(Long usuarioId, Long libroId);
    void desmarcar(Long usuarioId, Long libroId);
    boolean esFavorito(Long usuarioId, Long libroId);
}
