package com.example.personal_library_api.service.impl;

import com.example.personal_library_api.domain.model.Favorito;
import com.example.personal_library_api.domain.model.FavoritoId;
import com.example.personal_library_api.domain.repository.FavoritoRepository;
import com.example.personal_library_api.domain.repository.LibroRepository;
import com.example.personal_library_api.domain.repository.UsuarioRepository;
import com.example.personal_library_api.service.FavoritoService;
import com.example.personal_library_api.web.advice.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritoServiceImpl implements FavoritoService {
    private final FavoritoRepository favoritoRepo;
    private final UsuarioRepository usuarioRepo;
    private final LibroRepository libroRepo;

    @Override
    public List<Favorito> listar(Long usuarioId) {
        return favoritoRepo.findByUsuarioId(usuarioId);
    }

    @Override
    public void marcar(Long usuarioId, Long libroId) {
        if (favoritoRepo.existsByUsuarioIdAndLibroId(usuarioId, libroId)) return;

        var usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        var libro = libroRepo.findById(libroId)
                .orElseThrow(() -> new NotFoundException("Libro no encontrado"));

        var fav = new Favorito();
        fav.setId(new FavoritoId(usuarioId, libroId));
        fav.setUsuario(usuario);
        fav.setLibro(libro);
        fav.setCreatedAt(Instant.now());
        favoritoRepo.save(fav);
    }

    @Override
    public void desmarcar(Long usuarioId, Long libroId) {
        favoritoRepo.deleteByUsuarioIdAndLibroId(usuarioId, libroId);
    }

    @Override
    public boolean esFavorito(Long usuarioId, Long libroId) {
        return favoritoRepo.existsByUsuarioIdAndLibroId(usuarioId, libroId);
    }
}
