package com.github.bgomar.bgconsolelogger.chapters;

public class Chapter {
    private final String title;
    private final int lineNumber;

    public Chapter(String title, int lineNumber) {
        this.title = title;
        this.lineNumber = lineNumber;
    }

    public String getTitle() {
        return title;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}