package com.basepatterns.structual.bridge;

public abstract class Program {
    protected Developer developer;

    public Program(Developer developer) {
        this.developer = developer;
    }

    public abstract void developProgram();
}
