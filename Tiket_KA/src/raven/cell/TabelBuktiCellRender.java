package raven.cell;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TabelBuktiCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof ImageIcon) {
            // If the value is an ImageIcon, set it as the icon of the label
            label.setIcon((ImageIcon) value);
            label.setText(""); // Clear the text
        } else {
            // Handle other cases if needed
            label.setIcon(null);
            label.setText(value.toString());
        }

        return label;
    }
}
