package com.sistema.notificacao.service;

import com.sistema.notificacao.model.MensagemWhatSapp;
import com.sistema.notificacao.model.Notificacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EnviarNotificacaoService {

    private final RestTemplate restTemplate;

    @Value("${green.api.url}")
    private String apiUrl;

    @Value("${green.api.instance}")
    private String idInstance;

    @Value("${green.api.apitokeninstance}")
    private String apiTokenInstance;

    public EnviarNotificacaoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarNotificacao(Notificacao notificacao) throws Exception {
        switch (notificacao.getCanalEnvio()){

            case SMS -> {
                enviarMensagemSms(notificacao.getMensagem(),notificacao.getDestinatario());
            }
            case PUSH -> {
                enviarMensagemPush(notificacao.getMensagem(),notificacao.getDestinatario());
            }
            case EMAIL -> {
                enviarMensagemEmail(notificacao.getMensagem(),notificacao.getDestinatario());
            }
            case WHATSAPP -> {
                enviarNotificacaoWhatSapp(new MensagemWhatSapp(notificacao.getId(), notificacao.getDestinatario(), notificacao.getMensagem()));
            }
            default -> {
                log.info("Notificacao sem canal definido id: {}", notificacao.getId());
            }
        }
    }

    private void enviarMensagemEmail(String mensagem, String destinatario) {
        log.info("Enviando mensagem para canal EMAIL");
    }

    private void enviarMensagemPush(String mensagem, String destinatario) {
        log.info("Enviando mensagem para canal PUSH");
    }

    private void enviarMensagemSms(String mensagem, String destinatario) {
        log.info("Enviando mensagem para canal SMS");
    }

    private void enviarNotificacaoWhatSapp(MensagemWhatSapp mensagemWhatSapp) throws Exception{
        try{
            String url = apiUrl + "/waInstance" + idInstance + "/sendMessage/" + apiTokenInstance;
            ResponseEntity<String> result = restTemplate.postForEntity(url, mensagemWhatSapp, String.class);
        }catch (Exception e){
            log.info("Erro ao enviar mensagem com id {}", mensagemWhatSapp.getNotificacaoId());
            throw e;
        }
    }
}
