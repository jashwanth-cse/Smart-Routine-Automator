package com.smartroutine.model;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebAction extends Action {
    private final String url;

    public WebAction(String name, String url) {
        super(name);
        // Ensure URL starts with http:// or https://
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            this.url = "https://" + url;
        } else {
            this.url = url;
        }
    }

    @Override
    public void performAction() {
        System.out.println("Opening website: " + url);
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Cannot open browser on this system.");
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("Failed to open URL: " + url);
            e.printStackTrace();
        }
    }
}
