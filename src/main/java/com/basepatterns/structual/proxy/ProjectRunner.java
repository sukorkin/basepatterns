package com.basepatterns.structual.proxy;

public class ProjectRunner {
    public static void main(String[] args) {
        Project project = new ProxyProject("https://github.com/sukorkin/basepatterns");

        project.run();
    }
}
