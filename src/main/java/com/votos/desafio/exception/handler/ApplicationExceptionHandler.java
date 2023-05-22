package com.votos.desafio.exception.handler;

import com.votos.desafio.exception.IdObrigatorioException;
import com.votos.desafio.exception.PautaFechadaException;
import com.votos.desafio.exception.PautaNaoEncontradaException;
import com.votos.desafio.exception.VotoRepetidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//Handler global de erros da aplicação
//As exceções e seus respectivos tratamentos (status, mensagem de erro) devem ser registradas nesta classe
@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VotoRepetidoException.class)
    public Map<String, String> handleVotoRepetido(VotoRepetidoException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("tipoCampo: ", "erro");
        errorMap.put("mensagemErro: ", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PautaNaoEncontradaException.class)
    public Map<String, String> handlePautaNaoEncontrada(PautaNaoEncontradaException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("tipoCampo: ", "erro");
        errorMap.put("mensagemErro: ", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IdObrigatorioException.class)
    public Map<String, String> handleIdNaoFornecido(IdObrigatorioException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("tipoCampo: ", "erro");
        errorMap.put("mensagemErro: ", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(PautaFechadaException.class)
    public Map<String, String> handlePautaFechada(PautaFechadaException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("tipoCampo: ", "erro");
        errorMap.put("mensagemErro: ", ex.getMessage());
        return errorMap;
    }

}
