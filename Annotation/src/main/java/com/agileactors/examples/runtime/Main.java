package com.agileactors.examples.runtime;

import com.agileactors.examples.runtime.proxy.ServiceInvocationHandler;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {

        Service service = new ServiceImpl();

        Service proxyService = (Service) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new ServiceInvocationHandler(service)
        );

        proxyService.doSomething();
    }
}
