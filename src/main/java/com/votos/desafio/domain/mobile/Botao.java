package com.votos.desafio.domain.mobile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Botao {

    public Botao(String texto, String url) {
        this.texto = texto;
        this.url = url;
    }

    private String texto;
    private String url;
}
