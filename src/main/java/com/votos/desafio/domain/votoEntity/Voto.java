package com.votos.desafio.domain.votoEntity;

import com.votos.desafio.domain.pautaEntity.Pauta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Voto {
    @Id
    private Long idVoto;

    @ManyToOne
    @JoinColumn(name = "pauta_idPauta")
    private Pauta pauta;
    private String valor;
}
