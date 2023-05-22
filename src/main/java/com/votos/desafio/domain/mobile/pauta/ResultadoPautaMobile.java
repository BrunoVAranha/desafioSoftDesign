package com.votos.desafio.domain.mobile.pauta;

import com.votos.desafio.domain.mobile.Botao;
import com.votos.desafio.domain.mobile.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultadoPautaMobile {

    public ResultadoPautaMobile() {
        this.itens = List.of(new Item("OUTPUT_NUMERICO", "idVotosSim", "Votos SIM"),
                new Item("OUTPUT_NUMERICO", "idVotosNao", "Votos NAO"),
                new Item("OUTPUT_TEXTO", "idResultadoPauta", "Resultado da Pauta"));


        this.botaoVoltar = new Botao("VOLTAR", "http://localhost:8080/voltarInicio");
    }

    private final String tipo = "INFO";
    private final String titulo = "RESULTADO PAUTA";
    private List<Item> itens;
    private Botao botaoVoltar;
}
