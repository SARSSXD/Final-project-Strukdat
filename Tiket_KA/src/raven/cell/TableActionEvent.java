package raven.cell;

import javax.swing.JTable;

/**
 *
 * @author RAVEN
 */
public interface TableActionEvent {

    void onEdit(int row, JTable table);

    void onDelete(int row, JTable table);

    void onView(int row, JTable table);
}
