package com.example.appname.usecase;

import com.example.appname.gateway.SaveFooBarToDatabaseGateway;
import com.example.appname.model.FooBar;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Named
@Slf4j
@AllArgsConstructor
public class SaveFooBar {

    private SaveFooBarToDatabaseGateway saveFooBarToDatabaseGateway;

    public void execute(final FooBar fooBar) {
        try {
            // any business rules
            saveFooBarToDatabaseGateway.execute(fooBar);
        } catch (Exception e) {
            log.error("Error to save fooBar {} {}",
                    kv("foo", fooBar.getFoo()),
                    kv("bar", fooBar.getBar())
            );
            throw new RuntimeException(e);
        }
    }
}
