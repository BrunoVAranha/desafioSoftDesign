package com.votos.desafio.repository;

import com.votos.desafio.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    //metodo usado para contar os 'SIM' e 'NAO'

}
