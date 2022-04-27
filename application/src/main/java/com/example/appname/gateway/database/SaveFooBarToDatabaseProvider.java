package com.example.appname.gateway.database;

import com.example.appname.gateway.SaveFooBarToDatabaseGateway;
import com.example.appname.model.FooBar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SaveFooBarToDatabaseProvider implements SaveFooBarToDatabaseGateway {

    @Override
    public void execute(FooBar fooBar) {
        log.info("Saved to database");
    }

}
