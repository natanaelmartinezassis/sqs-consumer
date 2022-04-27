package com.example.appname.entrypoint.message.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class GenericMessage implements Serializable {

    private final String traceId;
    private final String spanId;
    private final Object message;

}
