package com.smartroutine.model;

import java.io.IOException;

public class SystemAction extends Action {
    private final String command;

    public SystemAction(String name, String command) {
        super(name);
        this.command = command.toLowerCase();
    }

    @Override
    public void performAction() {
        System.out.println("Executing system action: " + command);
        try {
            Process process;
            switch (command) {
                case "shutdown":
                    process = Runtime.getRuntime().exec("shutdown /s /t 0");
                    break;
                case "restart":
                    process = Runtime.getRuntime().exec("shutdown /r /t 0");
                    break;
                case "logoff":
                    process = Runtime.getRuntime().exec("shutdown /l");
                    break;
                case "sleep":
                    process = Runtime.getRuntime().exec("rundll32.exe powrprof.dll,SetSuspendState 0,1,0");
                    break;
                case "lock":
                    process = Runtime.getRuntime().exec("rundll32.exe user32.dll,LockWorkStation");
                    break;
                default:
                    System.out.println("Unknown system action: " + command);
                    return;
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to execute system action: " + command);
            e.printStackTrace();
        }
    }
}
