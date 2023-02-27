package com.basepatterns.creational.abstractfactory.banking;

import com.basepatterns.creational.abstractfactory.Developer;

public class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Java developer writes Java code ...");
    }
}
