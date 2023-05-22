package com.votos.desafio.domain.mobile.pauta;

import com.votos.desafio.domain.mobile.Botao;
import com.votos.desafio.domain.mobile.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class PautaMobile {

    public PautaMobile() {
        this.itens = Collections.singletonList(new Item("INPUT_TEXTO", "idCpf", "CPF"));
        this.botaoVotarSim = new Botao("SIM", "http://localhost:8080/votarNao");
        this.botaoVotarNao = new Botao("NAO", "http://localhost:8080/votarSim");
    }

    private final String tipo = "FORMULARIO";
    private final String titulo = "PAUTA - VOTAÇÃO";
    private List<Item> itens;
    private Botao botaoVotarSim;
    private Botao botaoVotarNao;
}
