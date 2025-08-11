package com.example.personal_library_api.domain.repository;

import com.example.personal_library_api.domain.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByUsuarioIdOrderByIdDesc(Long usuarioId);

    boolean existsByUsuarioIdAndTituloIgnoreCase(Long usuarioId, String titulo);
}
