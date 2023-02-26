package com.agileactors.examples.runtime.proxy;

import com.agileactors.examples.runtime.RuntimeAnnotation;
import com.agileactors.examples.runtime.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class ServiceInvocationHandler implements InvocationHandler {

    private static final Logger LOGGER = Logger.getLogger(Service.class.getName());

    private final Service serviceImpl;

    public ServiceInvocationHandler(Service service) {
        this.serviceImpl = service;
    }

    @Override
    public Object invoke(Object proxy, Method abstractMethod, Object[] args) throws Throwable {
        Method[] methods = serviceImpl.getClass().getMethods();

        for (Method implMethod : methods) {
            if (abstractMethod.getName().equals(implMethod.getName()) && implMethod.isAnnotationPresent(RuntimeAnnotation.class)) {
                long startTime = System.nanoTime();
                LOGGER.info("Method " + implMethod.getName() + " started");
                Object result = implMethod.invoke(serviceImpl, args);
                LOGGER.info("Method " + implMethod.getName() + " executed in " + (System.nanoTime() - startTime) + " nanoseconds");
                return result;
            }
        }
        return abstractMethod.invoke(serviceImpl, args);
    }
}
