package com.example.appname.gateway;

import com.example.appname.model.FooBar;

public interface SaveFooBarToDatabaseGateway {

    void execute(final FooBar fooBar);

}
