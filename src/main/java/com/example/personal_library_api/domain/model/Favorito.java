package com.example.personal_library_api.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table (name = "favoritos", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "libro_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Favorito {
    @EmbeddedId
    private FavoritoId id;

    @ManyToOne(fetch = FetchType.LAZY) @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY) @MapsId("libroId")
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

}

