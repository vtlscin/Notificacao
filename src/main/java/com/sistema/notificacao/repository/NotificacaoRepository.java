package com.sistema.notificacao.repository;

import com.sistema.notificacao.model.Notificacao;
import com.sistema.notificacao.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByStatusInAndDataHoraEnvioBefore(List<Status> status,LocalDateTime dataHoraEnvio);

}
