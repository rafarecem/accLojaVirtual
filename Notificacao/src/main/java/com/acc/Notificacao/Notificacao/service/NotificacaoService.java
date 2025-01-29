package com.acc.Notificacao.Notificacao.service;

import com.acc.Notificacao.Notificacao.models.Notificacao;
import com.acc.Notificacao.Notificacao.models.NotificacaoPayload;
import com.acc.Notificacao.Notificacao.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public Notificacao save(NotificacaoPayload payload) {
        var notificacao = new Notificacao(payload.getMensagem(), payload.getDataEnvio());
        return notificacaoRepository.save(notificacao);
    }

    public List<Notificacao> findAll() {
        return notificacaoRepository.findAll();
    }
}
