package com.agileactors.examples.runtime;

public class ServiceImpl implements Service {
    @Override
    @RuntimeAnnotation
    public void doSomething() {
        System.out.println("Do Something is running");
    }
}
