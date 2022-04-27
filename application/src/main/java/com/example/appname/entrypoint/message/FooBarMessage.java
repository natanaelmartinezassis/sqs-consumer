package com.example.appname.entrypoint.message;

import com.example.appname.model.FooBar;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FooBarMessage {

    private String foo;
    private String bar;
    private double value;

    public FooBar toDomain() {
        return FooBar.builder()
                .foo(this.getFoo())
                .bar(this.getBar())
                .value(this.getValue())
                .build();
    }

}
