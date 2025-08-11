package com.example.personal_library_api.service.impl;

import com.example.personal_library_api.domain.model.Lectura;
import com.example.personal_library_api.domain.model.Libro;
import com.example.personal_library_api.domain.model.Usuario;
import com.example.personal_library_api.domain.repository.LecturaRepository;
import com.example.personal_library_api.domain.repository.LibroRepository;
import com.example.personal_library_api.domain.repository.UsuarioRepository;
import com.example.personal_library_api.service.LecturaService;
import com.example.personal_library_api.service.dto.MarcarLecturaDTO;
import com.example.personal_library_api.web.advice.BusinessException;
import com.example.personal_library_api.web.advice.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class LecturaServiceImpl implements LecturaService {
    private final LecturaRepository lecturaRepo;
    private final UsuarioRepository usuarioRepo;
    private final LibroRepository libroRepo;

    @Override
    public List<Lectura> listarPorUsuario(Long usuarioId) {
        return lecturaRepo.findByUsuarioId(usuarioId);
    }

    @Override
    public Lectura marcar(Long usuarioId, Long libroId, MarcarLecturaDTO dto) {
        Usuario u = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        Libro l = libroRepo.findById(libroId)
                .orElseThrow(() -> new NotFoundException("Libro no encontrado"));

        // Regla: no más de una EN_PROCESO por (usuario, libro)
        if (dto.getEstado() == Lectura.Estado.EN_PROCESO) {
            long enCurso = lecturaRepo.countByUsuarioIdAndLibroIdAndEstado(
                    usuarioId, libroId, Lectura.Estado.EN_PROCESO);
            if (enCurso > 0) throw new BusinessException("Ya tienes una lectura en curso de este libro");
        }

        Lectura lec = new Lectura();
        lec.setUsuario(u);
        lec.setLibro(l);
        lec.setEstado(dto.getEstado() != null ? dto.getEstado() : Lectura.Estado.EN_PROCESO);
        lec.setComentario(dto.getComentario());

        LocalDate hoy = LocalDate.now();
        lec.setFechaInicio(dto.getFechaInicio() != null ? dto.getFechaInicio() : hoy);
        if (lec.getEstado() == Lectura.Estado.FINALIZADO) {
            lec.setFechaFin(dto.getFechaFin() != null ? dto.getFechaFin() : hoy);
        }

        return lecturaRepo.save(lec);
    }

}
