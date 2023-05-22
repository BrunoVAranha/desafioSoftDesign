package com.votos.desafio.domain.mobile;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    public Item(String tipo, String id, String titulo) {
        this.tipo = tipo;
        this.id = id;
        this.titulo = titulo;
    }

    private String tipo;
    private String id;
    private String titulo;
    private String valorTexto;

    private Long valorNum;


}
