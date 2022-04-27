package com.example.appname.entrypoint.message.support;

import com.example.appname.log.util.LogFields;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.support.converter.NotificationRequestConverter;
import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Component
public class NotificationMessageArgumentResolver implements HandlerMethodArgumentResolver {

    private final MessageConverter converter;
    private final ObjectMapper mapper;

    public NotificationMessageArgumentResolver(MessageConverter converter, ObjectMapper mapper) {
        this.converter = new NotificationRequestConverter(converter);
        this.mapper = mapper;
    }

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(NotificationMessage.class);
    }

    public Object resolveArgument(MethodParameter par, Message<?> msg) throws Exception {
        Object genericObject = this.converter.fromMessage(msg, GenericMessage.class);
        NotificationRequestConverter.NotificationRequest nr =
                (NotificationRequestConverter.NotificationRequest) genericObject;

        GenericMessage genericMessage = mapper.convertValue(nr.getMessage(), GenericMessage.class);
        MDC.put(LogFields.TRACE_ID.getKey(), genericMessage.getTraceId());
        MDC.put(LogFields.SPAN_ID.getKey(), genericMessage.getSpanId());

        Object message = mapper.convertValue(genericMessage.getMessage(), par.getParameterType());
        log.debug("{} with ID {} received from SQS queue {}",
                kv("sqs_message", message),
                kv("sqs_message_id", msg.getHeaders().get("MessageId")),
                kv("sqs_queue", msg.getHeaders().get("LogicalResourceId"))
        );

        return message;
    }
}
