package com.basepatterns.creational.abstractfactory.banking;

import com.basepatterns.creational.abstractfactory.Developer;
import com.basepatterns.creational.abstractfactory.ProjectManager;
import com.basepatterns.creational.abstractfactory.ProjectTeamFactory;
import com.basepatterns.creational.abstractfactory.Tester;

public class BankingTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new JavaDeveloper();
    }

    @Override
    public Tester getTester() {
        return new QATester();
    }

    @Override
    public ProjectManager getProjectManager() {
        return new BankingPM();
    }
}
