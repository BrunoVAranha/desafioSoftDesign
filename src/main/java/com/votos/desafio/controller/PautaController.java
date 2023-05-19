package com.votos.desafio.controller;

import com.votos.desafio.domain.Pauta;
import com.votos.desafio.exception.VotoRepetidoException;
import com.votos.desafio.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/pauta")
public class PautaController {

    @Autowired
    PautaService pautaService;

    @PostMapping("/iniciarPauta")
    public ResponseEntity<Pauta> iniciarPauta() throws VotoRepetidoException {
        Pauta novaPauta = new Pauta();
        pautaService.iniciarPauta(novaPauta);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(novaPauta);
    }



}
