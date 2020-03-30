package br.com.intelector.monitoramentorede.repository;

import br.com.intelector.monitoramentorede.model.HistoricoRede;
import br.com.intelector.monitoramentorede.domain.DominioSituacaoRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HistoricoRedeRepository extends JpaRepository<HistoricoRede, Long> {

    List<HistoricoRede> findBySituacao(DominioSituacaoRegistro situacao);

    @Query("SELECT a FROM HistoricoRede a WHERE a.dhAtu <= :data")
    List<HistoricoRede> findByDataRetroativa(Date data);

}
