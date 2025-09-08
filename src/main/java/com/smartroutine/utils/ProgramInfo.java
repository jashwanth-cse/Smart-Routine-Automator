package com.smartroutine.utils;

public class ProgramInfo {
    private final String name;      // display name
    private final String fullPath;  // actual path

    public ProgramInfo(String name, String fullPath) {
        this.name = name;
        this.fullPath = fullPath;
    }

    public String getName() {
        return name;
    }

    public String getFullPath() {
        return fullPath;
    }

    @Override
    public String toString() {
        return name; // UI shows only name
    }
}
