package br.com.intelector.monitoramentorede.service;

import br.com.intelector.monitoramentorede.service.dto.ConfiguracaoDTO;
import br.com.intelector.monitoramentorede.exception.CarregarConfiguracaoException;
import br.com.intelector.monitoramentorede.repository.ConfiguracaoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public String getValue(String nome) {
        try {
            return configuracaoRepository.findByNome(nome).get().getValor();
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    public ConfiguracaoDTO inicializarConfiguracao() throws CarregarConfiguracaoException {
        ConfiguracaoDTO configuracaoDTO = new ConfiguracaoDTO();
        log.info("Carregando configurações...");
        try {
            configuracaoDTO.setEmailTo(getValue("EMAIL_TO"));
            log.info("EMAIL_TO: {}", configuracaoDTO.getEmailTo());
            configuracaoDTO.setNotificarViaEmail(getValue("NOTIFICAR_VIA_EMAIL"));
            log.info("NOTIFICAR_VIA_EMAIL: {}", configuracaoDTO.getNotificarViaEmail());
            configuracaoDTO.setNotificarViaTelegram(getValue("NOTIFICAR_VIA_TELEGRAM"));
            log.info("NOTIFICAR_VIA_TELEGRAM: {}", configuracaoDTO.getNotificarViaTelegram());
        } catch (Exception e) {
            throw new CarregarConfiguracaoException(e);
        }
        return configuracaoDTO;
    }
}
