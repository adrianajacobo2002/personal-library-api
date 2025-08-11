package com.example.personal_library_api.service.impl;

import com.example.personal_library_api.domain.model.Libro;
import com.example.personal_library_api.domain.repository.LibroRepository;
import com.example.personal_library_api.domain.repository.UsuarioRepository;
import com.example.personal_library_api.service.LibroService;
import com.example.personal_library_api.service.dto.CrearLibroDTO;
import com.example.personal_library_api.service.dto.LibroDTO;
import com.example.personal_library_api.service.mapper.LibroMapper;
import com.example.personal_library_api.web.advice.BusinessException;
import com.example.personal_library_api.web.advice.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {
    private final LibroRepository libroRepo;
    private final UsuarioRepository usuarioRepo;

    @Override
    public List<LibroDTO> listar(Long usuarioId) {
        return libroRepo.findByUsuarioIdOrderByIdDesc(usuarioId)
                .stream().map(LibroMapper::toDto).toList();
    }

    @Override
    public LibroDTO crear(Long usuarioId, CrearLibroDTO dto) {
        var usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (libroRepo.existsByUsuarioIdAndTituloIgnoreCase(usuarioId, dto.getTitulo())) {
            throw new BusinessException("Ya tienes un libro con ese título");
        }
        Libro guardado = libroRepo.save(LibroMapper.toEntity(dto, usuario));
        return LibroMapper.toDto(guardado);
    }

    @Override
    public void eliminar(Long usuarioId, Long libroId) {
        var libro = libroRepo.findById(libroId)
                .orElseThrow(() -> new NotFoundException("Libro no encontrado"));
        if (!libro.getUsuario().getId().equals(usuarioId)) {
            throw new BusinessException("No puedes eliminar libros de otro usuario");
        }
        libroRepo.delete(libro);
    }
}
