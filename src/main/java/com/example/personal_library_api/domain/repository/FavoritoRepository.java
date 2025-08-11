package com.example.personal_library_api.domain.repository;

import com.example.personal_library_api.domain.model.Favorito;
import com.example.personal_library_api.domain.model.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {
    List<Favorito> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioIdAndLibroId(Long usuarioId, Long libroId);
    void deleteByUsuarioIdAndLibroId(Long usuarioId, Long libroId);
}
