package com.sistema.notificacao.dto;

import com.sistema.notificacao.model.Canal;
import com.sistema.notificacao.model.Status;
import com.sistema.notificacao.validation.CanalValidation;
import com.sistema.notificacao.validation.ValidaCelular;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CanalValidation
@ValidaCelular
public class NotificacaoDto {

    private LocalDateTime dataHoraEnvio;

    private String destinatario;

    private String mensagem;

    private Canal canalEnvio;

    private Status status;
}
