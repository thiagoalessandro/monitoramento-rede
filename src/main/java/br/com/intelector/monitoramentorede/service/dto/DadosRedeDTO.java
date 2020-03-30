package br.com.intelector.monitoramentorede.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosRedeDTO {

    private BigDecimal download;
    private BigDecimal upload;
    private BigDecimal latency;

}
