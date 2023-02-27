package com.basepatterns.creational.abstractfactory.website;

import com.basepatterns.creational.abstractfactory.Developer;
import com.basepatterns.creational.abstractfactory.ProjectManager;
import com.basepatterns.creational.abstractfactory.ProjectTeamFactory;
import com.basepatterns.creational.abstractfactory.Tester;

public class WebsiteTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new PhpDeveloper();
    }

    @Override
    public Tester getTester() {
        return new ManualTester();
    }

    @Override
    public ProjectManager getProjectManager() {
        return new WebsitePM();
    }
}
