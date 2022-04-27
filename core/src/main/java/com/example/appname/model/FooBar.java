package com.example.appname.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FooBar implements Serializable {

    private String foo;
    private String bar;
    private double value;

}
