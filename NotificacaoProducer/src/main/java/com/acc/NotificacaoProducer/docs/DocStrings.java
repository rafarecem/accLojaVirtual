package com.acc.NotificacaoProducer.docs;

public class DocStrings {

    public class Response {
        public static final String NO_CONTENT = "204";
    }

    public class ControllerDocs {
       public static final String TAG_NAME = "Notificações";
       public static final String TAG_DESC = "API para o envio de notificações";

       public static final String SEND_SUMM = "Envia uma notificação para a fila";
       public static final String SEND_DESC = "Recebe uma mensagem e a encaminha para a fila RabbitMQ para processamento assíncrono.";
    }

    public class NotificacaoPayloadDocs {
        public static final String CLASS_DESC = "Representa uma notificação enviada para a fila";

        public static final String MENSAGEM_DESC = "Conteúdo da mensagem de notificação";
        public static final String MENSAGEM_EXM = "Atualização do pedido concluída";

        public static final String DATA_DESC = "Data e hora do envio";
        public static final String DATA_EXM = "2025-01-27T15:30:00Z";

    }
}
