package com.basepatterns.creational.abstractfactory.website;

import com.basepatterns.creational.abstractfactory.Developer;

public class PhpDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("PHP developer writes PHP code ...");
    }
}
