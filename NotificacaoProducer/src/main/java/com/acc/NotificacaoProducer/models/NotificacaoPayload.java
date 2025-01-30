package com.acc.NotificacaoProducer.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

import static com.acc.NotificacaoProducer.docs.DocStrings.NotificacaoPayloadDocs.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = CLASS_DESC)
public class NotificacaoPayload implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = MENSAGEM_DESC, example = MENSAGEM_EXM)
    private String mensagem;

    @Schema(description = DATA_DESC, example = DATA_EXM)
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

