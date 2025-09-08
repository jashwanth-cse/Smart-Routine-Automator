package com.smartroutine.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProgramFetcher {
    private static final String ALL_USERS_PATH =
            "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs";
    private static final String USER_PATH =
            System.getenv("APPDATA") + "\\Microsoft\\Windows\\Start Menu\\Programs";

    public static List<ProgramInfo> getInstalledPrograms() {
        List<ProgramInfo> programs = new ArrayList<>();

        // Scan All Users Start Menu
        listPrograms(new File(ALL_USERS_PATH), programs);

        // Scan Current User Start Menu
        listPrograms(new File(USER_PATH), programs);

        return programs;
    }

    private static void listPrograms(File dir, List<ProgramInfo> programs) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File f : files) {
            if (f.isDirectory()) {
                listPrograms(f, programs);
            } else if (f.getName().endsWith(".lnk")) {
                String displayName = f.getName().replace(".lnk", "");
                String fullPath = f.getAbsolutePath();
                programs.add(new ProgramInfo(displayName, fullPath));
            }
        }
    }
}
