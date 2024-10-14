package com.sistema.notificacao.service;

import com.sistema.notificacao.dto.NotificacaoDto;
import com.sistema.notificacao.model.Notificacao;
import com.sistema.notificacao.model.Status;
import com.sistema.notificacao.repository.NotificacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class NotificacaoService {

    private NotificacaoRepository notificacaoRepository;

    private ModelMapper modelMapper;

    private EnviarNotificacaoService enviarNotifacao;

    public NotificacaoService(NotificacaoRepository notificacaoRepository, ModelMapper modelMapper, EnviarNotificacaoService enviarNotifacao) {
        this.notificacaoRepository = notificacaoRepository;
        this.modelMapper = modelMapper;
        this.enviarNotifacao = enviarNotifacao;
    }

    public void salvarNotificacao(NotificacaoDto dto){
        Notificacao notificacao = modelMapper.map(dto,Notificacao.class);
        notificacao.setStatus(Status.PENDENTE);
        notificacaoRepository.save(notificacao);
    }

    public NotificacaoDto recuperarNotificacao(Long id){
        Notificacao notificacao = notificacaoRepository.findById(id).orElseThrow();
        return modelMapper.map(notificacao,NotificacaoDto.class);
    }

    public void atualizaStatusNotificacao(Long id, Status status){
        Notificacao notificacao = notificacaoRepository.findById(id).orElseThrow();
        notificacao.setStatus(status);
        notificacaoRepository.save(notificacao);
    }

    private List<Notificacao> recuperaNotificacaoPorTempo(){
        log.info("Recuperando lista de notificacoes");
        LocalDateTime tempoAtual = LocalDateTime.now();
        return notificacaoRepository.findByStatusInAndDataHoraEnvioBefore(
                Arrays.asList(Status.PENDENTE,Status.ERRO_ENVIO),tempoAtual);
    }

    public void trataNotificacoes(){
        List<Notificacao> notificacoesSalvas = recuperaNotificacaoPorTempo();
        if(!notificacoesSalvas.isEmpty()){
            log.info("Quantidade de notificacoes {}",notificacoesSalvas.size());
            for(Notificacao notificacao : notificacoesSalvas){
                notificacao.setStatus(Status.ENVIADO);
                try {
                    enviarNotifacao.enviarNotificacao(notificacao);
                    log.info("Notificacao enviada para canal {}, com destinatário {}",notificacao.getCanalEnvio(), notificacao.getDestinatario());
                } catch (Exception e) {
                    notificacao.setStatus(Status.ERRO_ENVIO);
                }
                notificacaoRepository.save(notificacao);
            }
        }
        else {
            log.info("Nenhuma notificacao encontrada no tempo {}",LocalDateTime.now());
        }
    }

}

