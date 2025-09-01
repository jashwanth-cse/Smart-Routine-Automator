package com.smartroutine.model;

public class WebAction extends Action {
    private String url;

    public WebAction(String name, String url) {
        super(name);
        this.url = url;
    }

    @Override
    public void performAction() {
        System.out.println("Opening website: " + url);
        // Later: Desktop.getDesktop().browse(new URI(url));
    }
}
