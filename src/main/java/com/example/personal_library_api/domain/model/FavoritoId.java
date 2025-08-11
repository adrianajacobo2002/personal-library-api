package com.example.personal_library_api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FavoritoId implements Serializable {

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "libro_id")
    private Long libroId;

    private static final long serialVersionUID = 1L;
}