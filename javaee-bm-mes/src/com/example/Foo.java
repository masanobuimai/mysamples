package com.example;

import javax.inject.Inject;

public class Foo {
    @Inject
    private Bar bar;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Foo{");
        sb.append("bar=").append(bar);
        sb.append('}');
        return sb.toString();
    }
}
