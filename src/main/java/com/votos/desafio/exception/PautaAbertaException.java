package com.votos.desafio.exception;

public class PautaAbertaException extends Exception{

    public PautaAbertaException(){
        super("Espere a pauta atual acabar para abrir outra!");
    }
}
