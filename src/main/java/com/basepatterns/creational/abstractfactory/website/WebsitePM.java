package com.basepatterns.creational.abstractfactory.website;

import com.basepatterns.creational.abstractfactory.ProjectManager;

public class WebsitePM implements ProjectManager {
    public void manageProject() {
        System.out.println("Website PM manages website project ...");
    }
}
