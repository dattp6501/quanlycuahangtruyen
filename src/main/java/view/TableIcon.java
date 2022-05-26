package view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableIcon extends JFrame{
    public TableIcon(){
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] title = {"image","stt"};
        Object[][] data = {
            {new ImageIcon("images/thungrac.png"),"1"},
            {new ImageIcon("images/thungrac.png"),"2"}
        };
        DefaultTableModel model = new DefaultTableModel(data,title){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0, columnIndex).getClass();
            }
        };
        
        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setCellRenderer(new RendererImage());
        this.add(table);
    }
    /**
     * InnerTableIcon
     */
    public class RendererImage extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel lbl = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("images/thungrac.png")));
            return lbl;
        }
    }
    public static void main(String[] args) {
        TableIcon tableIcon = new TableIcon();
        tableIcon.setVisible(true);
    }
}
