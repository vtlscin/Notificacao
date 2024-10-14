package com.sistema.notificacao.scheduler;

import com.sistema.notificacao.model.Notificacao;
import com.sistema.notificacao.service.NotificacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ExecutorTarefas {

    @Autowired
    private NotificacaoService service;

    @Scheduled(fixedDelay = 1,timeUnit = TimeUnit.MINUTES)
    public void executa(){
      log.info("Executando tarefa de enviar notificacoes");
      service.trataNotificacoes();
    }

}
