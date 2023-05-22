package com.votos.desafio.controller;

import com.votos.desafio.domain.mobile.pauta.PautaMobile;
import com.votos.desafio.domain.mobile.pauta.ResultadoPautaMobile;
import com.votos.desafio.domain.mobile.pauta.VotoMobile;
import com.votos.desafio.domain.pautaEntity.Pauta;
import com.votos.desafio.domain.votoEntity.Voto;
import com.votos.desafio.exception.IdObrigatorioException;
import com.votos.desafio.exception.PautaFechadaException;
import com.votos.desafio.exception.PautaNaoEncontradaException;
import com.votos.desafio.exception.VotoRepetidoException;
import com.votos.desafio.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PautaMobile> iniciarPauta() throws PautaFechadaException {
        Pauta novaPauta = pautaService.iniciarPauta();
        iniciarTimer(novaPauta);
        return ResponseEntity.ok(new PautaMobile());
    }

    @PostMapping("/votarSim")
    public ResponseEntity<VotoMobile> votarSim(@RequestBody Voto voto) throws VotoRepetidoException, IdObrigatorioException, PautaFechadaException {
        if (pautaAberta) {
            pautaService.votarSim(voto.getIdVoto());
            return ResponseEntity.ok(new VotoMobile(pautaAberta));
        }
        throw new PautaFechadaException("Nehuma pauta aberta no momento.");
    }

    @PostMapping("/votarNao")
    public ResponseEntity<VotoMobile> votarNao(@RequestBody Voto voto) throws VotoRepetidoException, IdObrigatorioException, PautaFechadaException {
        if(pautaAberta) {
            pautaService.votarNao(voto.getIdVoto());
            return ResponseEntity.ok(new VotoMobile(pautaAberta));
        }
        throw new PautaFechadaException("Nehuma pauta aberta no momento.");
    }


    //obter o resultado de uma puta
    @GetMapping("/resultadoPauta/{idPauta}")
    public ResponseEntity<ResultadoPautaMobile> resultadoPauta(@PathVariable Long idPauta) throws PautaNaoEncontradaException {
        return ResponseEntity.ok(pautaService.obterResultadoPorId(idPauta));
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
