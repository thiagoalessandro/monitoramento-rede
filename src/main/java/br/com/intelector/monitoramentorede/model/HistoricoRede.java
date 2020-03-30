package br.com.intelector.monitoramentorede.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "tbl_historico_rede")
public class HistoricoRede extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_historico")
    private Date dataHoraHistorico;

    @Column(name = "latency")
    private BigDecimal latency;

    @Column(name = "download")
    private BigDecimal download;

    @Column(name = "upload")
    private BigDecimal upload;

}
