package com.smartroutine.model;

public class ReminderAction extends Action {
    private String message;

    public ReminderAction(String name, String message) {
        super(name);
        this.message = message;
    }

    @Override
    public void performAction() {
        System.out.println("Reminder: " + message);
    }
}
