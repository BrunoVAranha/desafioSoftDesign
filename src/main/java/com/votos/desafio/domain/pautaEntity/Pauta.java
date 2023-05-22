package com.votos.desafio.domain.pautaEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Pauta {

    public Pauta() {
        this.votosNao = 0L;
        this.votosSim = 0L;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPauta;
    private Long votosSim;
    private Long votosNao;
    private String resultado;
}
