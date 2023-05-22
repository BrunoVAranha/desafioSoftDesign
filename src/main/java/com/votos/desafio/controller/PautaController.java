package com.votos.desafio.controller;

import com.votos.desafio.domain.mobile.pauta.PautaMobile;
import com.votos.desafio.domain.mobile.pauta.ResultadoPautaMobile;
import com.votos.desafio.domain.mobile.pauta.VotoMobile;
import com.votos.desafio.domain.pautaEntity.Pauta;
import com.votos.desafio.domain.votoEntity.Voto;
import com.votos.desafio.exception.PautaNaoEncontradaException;
import com.votos.desafio.exception.VotoRepetidoException;
import com.votos.desafio.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class PautaController {

    @Autowired
    PautaService pautaService;
    //flag que indica se a pauta está aberta ou não
    private boolean pautaAberta = false;

    //endpoint para iniciar a pauta e começar a contagem de tempo
    @PostMapping("/iniciarPauta")
    public PautaMobile iniciarPauta() {
        Pauta novaPauta = pautaService.iniciarPauta();
        iniciarTimer(novaPauta);
        return new PautaMobile();
    }

    @PostMapping("/votarSim")
    public VotoMobile votarSim(@RequestBody Voto voto) throws VotoRepetidoException {
        if (pautaAberta) {
            pautaService.votarSim(voto.getIdVoto());
            return new VotoMobile(pautaAberta);
        } else {
            return new VotoMobile(pautaAberta);
        }
    }

    @PostMapping("/votarNao")
    public VotoMobile votarNao(@RequestBody Voto voto) throws VotoRepetidoException {
        if (pautaAberta) {
            pautaService.votarNao(voto.getIdVoto());
            return new VotoMobile(pautaAberta);
        } else {
            return new VotoMobile(pautaAberta);
        }
    }



    //obter o resultado de uma puta
    @GetMapping("/resultadoPauta/{idPauta}")
    public ResultadoPautaMobile resultadoPauta(@PathVariable Long idPauta) throws PautaNaoEncontradaException {
        return pautaService.obterResultadoPorId(idPauta);
    }

    private void iniciarTimer(Pauta novaPauta) {
        //criação de uma nova thread, que irá alternar o valor de pautaAberta quando o tempo duration for atingido.
        ScheduledExecutorService timerExecutorService = Executors.newSingleThreadScheduledExecutor();
        pautaAberta = true;

        //Escolher o tempo de vida da thread
        long duration = 60;

        //fechar a pauta após o tempo ser atingido.
        timerExecutorService.schedule(() -> {
            pautaAberta = false;
            pautaService.finalizarPauta(novaPauta);

        }, duration, TimeUnit.SECONDS);
    }
}
