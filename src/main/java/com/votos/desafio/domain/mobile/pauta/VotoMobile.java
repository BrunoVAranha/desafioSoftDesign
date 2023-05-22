package com.votos.desafio.domain.mobile.pauta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.votos.desafio.domain.mobile.Botao;
import com.votos.desafio.domain.mobile.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VotoMobile {

    public VotoMobile(Boolean pautaAberta) {
        this.botaoVoltar = new Botao("VOLTAR", "http://localhost:8080/voltarInicio");
        this.titulo = pautaAberta?"Obrigado por votar!" : "Pauta encerrada!";
    }

    private final String tipo = "INFO";
    private String titulo;
    private List<Item> itens;
    private Botao botaoVoltar;
}
