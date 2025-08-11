package com.example.personal_library_api.domain.repository;

import com.example.personal_library_api.domain.model.Lectura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LecturaRepository extends JpaRepository<Lectura, Long> {
    List<Lectura> findByUsuarioId(Long usuarioId);
    List<Lectura> findByUsuarioIdAndEstado(Long usuarioId, Lectura.Estado estado);
    Optional <Lectura> findFirstByUsuarioIdAndLibroIdOrderByIdDesc(Long usuarioId, Long libroId);
    long countByUsuarioIdAndLibroIdAndEstado(Long usuarioId, Long libroId, Lectura.Estado estado);
}
