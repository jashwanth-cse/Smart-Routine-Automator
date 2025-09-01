package com.smartroutine.model;

public class SystemAction extends Action {
    private String command;

    public SystemAction(String name, String command) {
        super(name);
        this.command = command;
    }

    @Override
    public void performAction() {
        System.out.println("Executing system command: " + command);
        // Later: Runtime.getRuntime().exec(command);
    }
}
