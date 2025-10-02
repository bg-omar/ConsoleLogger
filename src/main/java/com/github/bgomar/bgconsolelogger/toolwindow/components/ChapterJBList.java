package com.github.bgomar.bgconsolelogger.toolwindow.components;

import com.intellij.ui.components.JBList;

import javax.swing.*;
import java.awt.*;

/**
 * Custom list used for the Chapters tool that limits its preferred scrollable viewport height
 * so it does not expand to the full height of all rows (which would suppress scrollbars).
 */
public class ChapterJBList extends JBList<String> {

    // Soft caps to keep things reasonable
    private int maxVisibleRows = 24;    // absolute cap
    private int defaultBaseRows = 12;   // base rows when many items

    public ChapterJBList() {
        super();
        // A prototype value to ensure a stable width calculation (can be overridden later)
        setPrototypeCellValue("Subsection 000: Prototype Width");
        setFocusable(true);
    }

    /**
     * Allow callers to tune the maximum rows used to compute preferred viewport height.
     */
    public void setMaxVisibleRows(int maxVisibleRows) {
        if (maxVisibleRows > 0) this.maxVisibleRows = maxVisibleRows;
    }

    public void setDefaultBaseRows(int defaultBaseRows) {
        if (defaultBaseRows > 0) this.defaultBaseRows = defaultBaseRows;
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        // Start with super's idea (gives us a reasonable width)
        Dimension d = super.getPreferredScrollableViewportSize();

        ListModel<String> model = getModel();
        int total = model == null ? 0 : model.getSize();

        // Determine how many rows to show (soft cap so scrollbar can appear)
        int rows;
        if (total == 0) {
            rows = defaultBaseRows; // empty list still gets a modest height
        } else if (total <= defaultBaseRows) {
            rows = total; // show all if small
        } else {
            rows = Math.min(Math.max(defaultBaseRows, (int) Math.ceil(defaultBaseRows * 1.25)), maxVisibleRows);
        }

        int cellHeight = getFixedCellHeight();
        if (cellHeight <= 0) {
            // Estimate using font metrics, add a little vertical padding
            FontMetrics fm = getFontMetrics(getFont());
            cellHeight = fm.getHeight() + 4;
        }

        d.height = cellHeight * rows;
        return d;
    }
}