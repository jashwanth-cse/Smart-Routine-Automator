package com.smartroutine.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Trigger {
    private String time; // "IMMEDIATE" or "HH:MM"

    public Trigger(String time) {
        this.time = time;
    }

    public boolean isTriggered() {
        if ("IMMEDIATE".equals(time)) {
            return true;
        }
        String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        return now.equals(time);
    }

    public String getTime() {
        return time;
    }
}
