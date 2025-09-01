package com.smartroutine.model;

import java.util.ArrayList;
import java.util.List;

public class Routine {
    private String name;
    private Trigger trigger;
    private List<Action> actions;

    public Routine(String name, Trigger trigger) {
        this.name = name;
        this.trigger = trigger;
        this.actions = new ArrayList<>();
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void execute() {
        System.out.println("\nExecuting routine: " + name);
        for (Action action : actions) {
            action.performAction();
        }
    }

    //  Add getters
    public String getName() {
        return name;
    }

    public Trigger getTrigger() {
        return trigger;
    }
}
