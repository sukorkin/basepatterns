package com.basepatterns.creational.abstractfactory.banking;

import com.basepatterns.creational.abstractfactory.ProjectManager;

public class BankingPM implements ProjectManager {
    public void manageProject() {
        System.out.println("Banking PM manages banking project ...");
    }
}
