package com.votos.desafio.service;

import com.votos.desafio.domain.mobile.pauta.ResultadoPautaMobile;
import com.votos.desafio.domain.pautaEntity.Pauta;
import com.votos.desafio.domain.votoEntity.Voto;
import com.votos.desafio.exception.PautaNaoEncontradaException;
import com.votos.desafio.exception.VotoRepetidoException;
import com.votos.desafio.repository.PautaRepository;
import com.votos.desafio.repository.VotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock ;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private VotoRepository votoRepository;

    Pauta pauta = new Pauta();

    PautaService pautaService;

    @BeforeEach
    void initService() {
        MockitoAnnotations.openMocks(this);
        pautaService = new PautaService(pauta, pautaRepository, votoRepository);
    }

    @Test
    public void iniciarPautaTest() {
        when(pautaRepository.save(Mockito.any(Pauta.class))).thenReturn(pauta);
        Assertions.assertNotNull(pautaService.iniciarPauta());
    }

    @Test
    public void votarSimTest() throws VotoRepetidoException {
        Voto voto = pautaService.votarSim(123L);
        Assertions.assertEquals("SIM", voto.getValor());
    }

    @Test
    public void votarSimRepetidoTest() {
        when(votoRepository.findByIdAndPauta(Mockito.any(), Mockito.any())).thenReturn(new Voto());
        Assertions.assertThrows(VotoRepetidoException.class, () -> pautaService.votarSim(123L));
    }

    @Test
    public void votarNaoTest() throws VotoRepetidoException {
        Voto voto = pautaService.votarNao(123L);
        Assertions.assertEquals("NAO", voto.getValor());
    }

    @Test
    public void votarNaoRepetidoTest() {
        when(votoRepository.findByIdAndPauta(Mockito.any(), Mockito.any())).thenReturn(new Voto());
        Assertions.assertThrows(VotoRepetidoException.class, () -> pautaService.votarSim(123L));
    }

    @Test
    public void finalizarPautaResultadoSimTest() {
        Pauta pauta = new Pauta();
        pauta.setVotosSim(1L);
        when(pautaRepository.save(Mockito.any(Pauta.class))).thenReturn(pauta);
        pautaService.finalizarPauta(pauta);
        Assertions.assertEquals("SIM", pauta.getResultado());
    }

    @Test
    public void finalizarPautaResultadoNaoTest() {
        Pauta pauta = new Pauta();
        pauta.setVotosNao(1L);
        when(pautaRepository.save(Mockito.any(Pauta.class))).thenReturn(pauta);
        pautaService.finalizarPauta(pauta);
        Assertions.assertEquals("NAO", pauta.getResultado());
    }

    @Test
    public void finalizarPautaResultadoEmpateTest() {
        Pauta pauta = new Pauta();
        pauta.setVotosNao(1L);
        pauta.setVotosSim(1L);
        when(pautaRepository.save(Mockito.any(Pauta.class))).thenReturn(pauta);
        pautaService.finalizarPauta(pauta);
        Assertions.assertEquals("EMPATE", pauta.getResultado());
    }

    @Test
    public void obterResultadoPorIdTest() throws PautaNaoEncontradaException {
        Pauta pauta = new Pauta();
        pauta.setVotosNao(1L);
        pauta.setVotosSim(2L);
        pauta.setResultado("SIM");
        when(pautaRepository.getReferenceById(anyLong())).thenReturn(pauta);
        ResultadoPautaMobile resultadoPautaMobile = pautaService.obterResultadoPorId(123L);
        Assertions.assertEquals(pauta.getVotosSim(), resultadoPautaMobile.getItens().get(0).getValorNum());
        Assertions.assertEquals(pauta.getVotosNao(), resultadoPautaMobile.getItens().get(1).getValorNum());
        Assertions.assertEquals(pauta.getResultado(), resultadoPautaMobile.getItens().get(2).getValorTexto());
    }

}