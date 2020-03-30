package br.com.intelector.monitoramentorede.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SchedulerMoniotramentoRedeService {


    @Autowired
    private HistoricoRedeService historicoRedeService;

    @Scheduled(cron = "${app.scheduler.coletarDadosRede}")
    public void coletarDadosRede() {
        log.info("Registrando dados da rede...");
        historicoRedeService.registrarHistoricoRede();
    }

    @Scheduled(cron = "${app.scheduler.removerHistorico}")
    public void removerHistorico() {
        log.info("Remover histórico de rede...");
        historicoRedeService.removerHistorico();
    }

}
