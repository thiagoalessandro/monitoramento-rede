package br.com.intelector.monitoramentorede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MonitoramentoRedeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoramentoRedeApplication.class, args);
    }

}
