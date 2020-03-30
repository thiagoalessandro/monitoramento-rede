package br.com.intelector.monitoramentorede.service;

import br.com.intelector.monitoramentorede.model.HistoricoRede;
import br.com.intelector.monitoramentorede.repository.HistoricoRedeRepository;
import br.com.intelector.monitoramentorede.service.dto.DadosRedeDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class HistoricoRedeService {

    @Autowired
    private HistoricoRedeRepository historicoRedeRepository;

    public void registrarHistoricoRede() {
        HistoricoRede historicoRede = new HistoricoRede();
        DadosRedeDTO dadosRedeDTO = coletarDadosRede();
        historicoRede.setDownload(dadosRedeDTO.getDownload());
        historicoRede.setUpload(dadosRedeDTO.getUpload());
        historicoRede.setLatency(dadosRedeDTO.getLatency());
        historicoRede.setDataHoraHistorico(DateUtils.addHours(new Date() , 3));
        historicoRedeRepository.save(historicoRede);
    }

    private DadosRedeDTO coletarDadosRede(){
        String s;
        DadosRedeDTO dadosRedeDTO = new DadosRedeDTO();
        try {
            Process p = Runtime.getRuntime().exec("/tmp/app/speedtest");
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = inputStream.readLine()) != null) {
                if(s.contains("Download"))
                    dadosRedeDTO.setDownload(new BigDecimal(s.substring(s.indexOf("Download") + 10, s.indexOf("Mbps")).trim()));
                if(s.contains("Upload"))
                    dadosRedeDTO.setUpload(new BigDecimal(s.substring(s.indexOf("Upload") + 10, s.indexOf("Mbps")).trim()));
                if(s.contains("Latency"))
                    dadosRedeDTO.setLatency(new BigDecimal(s.substring(s.indexOf("Latency") + 10, s.indexOf("ms")).trim()));
            }
            p.destroy();
            inputStream.close();
            log.info("Download {}, Upload: {}, Latency: {}", dadosRedeDTO.getDownload(), dadosRedeDTO.getUpload(), dadosRedeDTO.getLatency());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dadosRedeDTO;
    }

    public void removerHistorico(){
        List<HistoricoRede> historicoRedes =  historicoRedeRepository.findByDataRetroativa(dataAnterior());
        if(!historicoRedes.isEmpty()) {
            log.info("{} historico(s) a serem removidos", historicoRedes.size());
            historicoRedes.forEach(this::deletar);
        }
    }

    public Date dataAnterior(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE , -240);
        return cal.getTime();
    }

    public void deletar(HistoricoRede historicoRede) {
        log.info("Historico removido: {}", historicoRede);
        historicoRedeRepository.delete(historicoRede);
    }

}
