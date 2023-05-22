package com.votos.desafio.repository;

import com.votos.desafio.domain.votoEntity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query(value = "SELECT * FROM voto WHERE id_voto = ?1 and pauta_id_pauta = ?2", nativeQuery = true)
    public Voto findByIdAndPauta(Long idVoto, Long idPauta);

}
