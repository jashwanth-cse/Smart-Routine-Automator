package com.smartroutine.model;

import java.io.IOException;

public class AppAction extends Action {
    private String programPath;  // full path or executable name

    public AppAction(String name, String programPath) {
        super(name);
        this.programPath = programPath;
    }

    @Override
    public void performAction() {
        try {
            // Use "cmd /c start" so Windows resolves it
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "", programPath);
            pb.start();
            System.out.println("Opened program: " + programPath);
        } catch (IOException e) {
            System.out.println("Failed to open program: " + programPath);
            e.printStackTrace();
        }
    }
}
