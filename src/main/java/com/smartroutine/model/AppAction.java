package com.smartroutine.model;

import java.io.IOException;

public class AppAction extends Action {
    private String program;

    public AppAction(String name, String program) {
        super(name);
        this.program = program;
    }

    @Override
    public void performAction() {
        System.out.println("Opening: " + program);
        try {
            // Windows will resolve program shortcut automatically
            new ProcessBuilder("cmd", "/c", "start", "", "\"" + program + "\"").start();
        } catch (IOException e) {
            System.out.println("Failed to open: " + program);
        }
    }
}
