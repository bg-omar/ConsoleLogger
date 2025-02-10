package com.github.bgomar.bgconsolelogger.tools;

import java.util.List;
import java.util.Locale;

public class ChapterTools {

    public static final List<String> CHAPTERS = List.of(
        "Chapter 1",
        "Chapter 2");


    public static String generateChapter(String chapter) {
        chapter = chapter.toLowerCase(Locale.ROOT);
        return chapter;
    }
}