package com.github.bgomar.bgconsolelogger.chapters;

import com.github.bgomar.bgconsolelogger.chapters.Chapter.Type;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.*;
import com.intellij.ui.JBColor;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ChapterScrollBarHighlighter {
    private final List<RangeHighlighter> highlighters = new ArrayList<>();

    public void highlightChapters(Editor editor, List<Chapter> chapters) {
        removeAll(editor);
        MarkupModel markupModel = editor.getMarkupModel();
        for (Chapter chapter : chapters) {
            int line = chapter.getLineNumber() - 1;
            int startOffset = editor.getDocument().getLineStartOffset(line);
            int endOffset = editor.getDocument().getLineEndOffset(line);
            TextAttributes attributes = new TextAttributes();
            attributes.setEffectType(EffectType.LINE_UNDERSCORE);
            attributes.setEffectColor(getColorForType(chapter.getType()));
            attributes.setBackgroundColor(getColorForType(chapter.getType()));
            RangeHighlighter highlighter = markupModel.addRangeHighlighter(
                startOffset,
                endOffset,
                HighlighterLayer.SELECTION - 1,
                attributes,
                HighlighterTargetArea.LINES_IN_RANGE
            );
            highlighter.setThinErrorStripeMark(false);
            highlighter.setErrorStripeMarkColor(getColorForType(chapter.getType()));
            highlighter.setErrorStripeTooltip(chapter.getTitle());
            highlighters.add(highlighter);
        }
    }

    public void removeAll(Editor editor) {
        for (RangeHighlighter highlighter : highlighters) {
            editor.getMarkupModel().removeHighlighter(highlighter);
        }
        highlighters.clear();
    }

    private JBColor getColorForType(Type type) {
        return switch (type) {
            case CHAPTER -> JBColor.BLUE;
            case SECTION -> JBColor.GREEN;
            case SUBSECTION -> JBColor.ORANGE;
            default -> JBColor.DARK_GRAY;
        };
    }
}
