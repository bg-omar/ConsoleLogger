package com.github.bgomar.consolelogger.toolwindow;

import javax.swing.*;

public class ComboBoxWithImageItem {
    private final String title;
    private final String iconPath;

    public ComboBoxWithImageItem(String title, String iconPath) {
        this.title = title;
        this.iconPath = iconPath;
    }

    public String displayName() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public String getIconPath() {
        return iconPath;
    }

    @Override
    public String toString() {
        return title;
    }
}