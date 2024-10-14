package com.sistema.notificacao.controller;

import com.sistema.notificacao.dto.NotificacaoDto;
import com.sistema.notificacao.model.Status;
import com.sistema.notificacao.service.EnviarNotificacaoService;
import com.sistema.notificacao.service.NotificacaoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificacao")
@Slf4j
public class NotificacaoController {

    private NotificacaoService notificacaoService;

    @Autowired
    private EnviarNotificacaoService enviarNotifacao;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> salvaNotificacao(@Valid @RequestBody NotificacaoDto notificacaoDto){
        log.info("Nova notificacao");
        notificacaoService.salvarNotificacao(notificacaoDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{notificacaoId}")
    public ResponseEntity<NotificacaoDto> recuperaNotificacao(@PathVariable Long notificacaoId){
        log.info("Recuperando notificacao {}", notificacaoId);
        NotificacaoDto dto = notificacaoService.recuperarNotificacao(notificacaoId);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{notificacaoId}")
    public ResponseEntity<Void> cancelarNotificacao(@PathVariable Long notificacaoId){
        log.info("Cancelando notificacao {}", notificacaoId);
        notificacaoService.atualizaStatusNotificacao(notificacaoId, Status.CANCELADO);
        return ResponseEntity.ok().build();
    }
}
