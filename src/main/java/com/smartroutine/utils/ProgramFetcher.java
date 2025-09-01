package com.smartroutine.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProgramFetcher {
    private static final String START_MENU_PATH =
            "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs";

    public static List<String> getInstalledPrograms() {
        List<String> programs = new ArrayList<>();
        File folder = new File(START_MENU_PATH);

        listPrograms(folder, programs);
        return programs;
    }

    private static void listPrograms(File dir, List<String> programs) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File f : files) {
            if (f.isDirectory()) {
                listPrograms(f, programs);
            } else if (f.getName().endsWith(".lnk")) {
                programs.add(f.getName().replace(".lnk", ""));
            }
        }
    }
}
