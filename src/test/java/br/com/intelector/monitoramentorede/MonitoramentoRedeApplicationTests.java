package br.com.intelector.monitoramentorede;

import br.com.intelector.monitoramentorede.service.SchedulerMoniotramentoRedeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class MonitoramentoRedeApplicationTests {

    @Autowired
    private SchedulerMoniotramentoRedeService schedulerMoniotramentoRedeService;

    @Test
    void gravarDadosRede() {
        try {
            schedulerMoniotramentoRedeService.coletarDadosRede();
        } catch (Exception e) {
            fail("Erro ao gravar dados da rede", e);
        }
    }

}
