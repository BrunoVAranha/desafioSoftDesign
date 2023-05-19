package com.votos.desafio.service;

import com.votos.desafio.domain.Pauta;
import com.votos.desafio.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    @Autowired
    PautaRepository pautaRepository;

    public Pauta iniciarPauta(Pauta novaPauta){
        iniciarContagemVotos(novaPauta);
        return pautaRepository.save(novaPauta);
    }

    private void iniciarContagemVotos(Pauta novaPauta){

    }

}
