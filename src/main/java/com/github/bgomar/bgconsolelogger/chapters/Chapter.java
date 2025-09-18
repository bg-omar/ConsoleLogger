package com.github.bgomar.bgconsolelogger.chapters;

public class Chapter {
    public enum Type {
        CHAPTER, SECTION, SUBSECTION
    }

    private final String title;
    private final int lineNumber;
    private final Type type;

    public Chapter(String title, int lineNumber, Type type) {
        this.title = title;
        this.lineNumber = lineNumber;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public Type getType() {
        return type;
    }
}