package com.pavlyshyn.menu;

public class DescriptionAction {
    String description;
    Actionable action;

    public DescriptionAction(String description, Actionable action) {
        this.description = description;
        this.action = action;

    }

    public String getDescription() {
        return description;
    }

    public Actionable getAction() {
        return action;
    }
}
