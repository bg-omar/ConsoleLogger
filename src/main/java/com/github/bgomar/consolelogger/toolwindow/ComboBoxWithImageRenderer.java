package com.github.bgomar.consolelogger.toolwindow;

import javax.swing.*;
import java.awt.*;

public class ComboBoxWithImageRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof ComboBoxWithImageItem) {
            ComboBoxWithImageItem item = (ComboBoxWithImageItem) value;
            setIcon(new ImageIcon(item.getIconPath()));
            setText(item.getTitle());
        }

        return this;
    }
}