package com.sistema.notificacao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MensagemWhatSapp {

    @JsonIgnore
    private Long notificacaoId;

    private String chatId;

    private String message;

    public MensagemWhatSapp(Long notificacaoId, String chatId, String message) {
        this.notificacaoId = notificacaoId;
        this.chatId = "55" + chatId + "@c.us";
        this.message = message;
    }
}
