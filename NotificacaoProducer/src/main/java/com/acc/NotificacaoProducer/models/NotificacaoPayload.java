package com.acc.NotificacaoProducer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoPayload implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private String mensagem;

    private ZonedDateTime dataEnvio;

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj != null && obj.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "NotificacaoPayload[]";
    }
}

