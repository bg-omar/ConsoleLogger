package com.github.bgomar.consolelogger.regex;

import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;

import java.util.List;
import java.util.Objects;

public record State(String regexp, String text, String replacement, int flags, MatchType matchType, List<Pair<TextRange, String>> groupPositions) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return flags == state.flags
                && Objects.equals(regexp, state.regexp)
                && Objects.equals(text, state.text)
                && Objects.equals(replacement, state.replacement)
                && matchType == state.matchType;
    }

    @Override
    public int hashCode() {
        return regexp.hashCode();
    }

}