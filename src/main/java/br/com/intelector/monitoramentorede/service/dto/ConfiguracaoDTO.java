package br.com.intelector.monitoramentorede.service.dto;

import lombok.Data;

@Data
public class ConfiguracaoDTO {

    private String emailTo;
    private String notificarViaEmail;
    private String notificarViaTelegram;

}
