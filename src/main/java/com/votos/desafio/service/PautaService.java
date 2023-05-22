package com.votos.desafio.service;

import com.votos.desafio.domain.mobile.pauta.ResultadoPautaMobile;
import com.votos.desafio.domain.pautaEntity.Pauta;
import com.votos.desafio.domain.votoEntity.Voto;
import com.votos.desafio.exception.IdObrigatorioException;
import com.votos.desafio.exception.PautaNaoEncontradaException;
import com.votos.desafio.exception.VotoRepetidoException;
import com.votos.desafio.repository.PautaRepository;
import com.votos.desafio.repository.VotoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PautaService {

    Pauta novaPauta;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    VotoRepository votoRepository;

    public Pauta iniciarPauta() {
        novaPauta = new Pauta();
        pautaRepository.save(novaPauta);
        return novaPauta;
    }

    public Voto votarSim(Long idVoto) throws VotoRepetidoException, IdObrigatorioException {
        Voto voto = new Voto();
        //verificar se o id já votou nesta pauta
        if(votoRepository.findByIdAndPauta(idVoto, novaPauta.getIdPauta()) == null){
            if(idVoto == null)
                throw new IdObrigatorioException("É obrigatório fornecer um ID para votar.");
            voto.setIdVoto(idVoto);
            voto.setPauta(novaPauta);
            voto.setValor("SIM");
            votoRepository.save(voto);
            novaPauta.setVotosSim(novaPauta.getVotosSim() + 1);
            return voto;
        }
        else{
            throw new VotoRepetidoException();
        }
    }

    public Voto votarNao(Long idVoto) throws VotoRepetidoException, IdObrigatorioException {
        Voto voto = new Voto();
        //verificar se o id já votou nesta pauta
        if(votoRepository.findByIdAndPauta(idVoto, novaPauta.getIdPauta()) == null){
            if(idVoto == null)
                throw new IdObrigatorioException("É obrigatório fornecer um ID para votar.");
            voto.setIdVoto(idVoto);
            voto.setPauta(novaPauta);
            voto.setValor("NAO");
            votoRepository.save(voto);
            novaPauta.setVotosNao(novaPauta.getVotosNao() + 1);
            return voto;
        }
        else{
            throw new VotoRepetidoException();
        }
    }

    public void finalizarPauta(Pauta novaPauta) {
        novaPauta.setResultado(calcularResultado(novaPauta));
        pautaRepository.save(novaPauta);
    }

    //obtém uma pauta por id, e formata para a estrutura mobile
    public ResultadoPautaMobile obterResultadoPorId(Long idPauta) throws PautaNaoEncontradaException {
        try {
            Pauta pauta = pautaRepository.getReferenceById(idPauta);
            ResultadoPautaMobile resultadoPauta = new ResultadoPautaMobile();
            resultadoPauta.getItens().get(0).setValorNum(pauta.getVotosSim());
            resultadoPauta.getItens().get(1).setValorNum(pauta.getVotosNao());
            resultadoPauta.getItens().get(2).setValorTexto(pauta.getResultado());
            return resultadoPauta;
        } catch (Exception e) {
            throw new PautaNaoEncontradaException("Pauta de id " + idPauta + " nao encontrada.");
        }
    }

    private String calcularResultado(Pauta novaPauta) {
        Long votosSim = novaPauta.getVotosSim();
        Long votosNao = novaPauta.getVotosNao();
        if (votosSim > votosNao) {
            return "SIM";
        } else if (votosSim < votosNao) {
            return "NAO";
        }
        return "EMPATE";
    }
}
