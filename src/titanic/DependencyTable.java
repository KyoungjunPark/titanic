package titanic;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

public class DependencyTable extends JPanel {

	/**
	 * DSM 정보를 받아와서 화면에 표시할 테이블을 만듭니다. DSM 정보는 ArrayList<ArrayList<String>>의
	 * 형식이며 ArrayList<String>은 [name , 0, 1, other dependencies...]의 형식의 데이터를
	 * 저장하고 있습니다.
	 */
	private ArrayList<ArrayList<String>> rows;

	DependencyTable(ArrayList<ArrayList<String>> rows, boolean showRowLabels)
			throws NullPointerException {

		init(rows, showRowLabels);
	}

	private void init(ArrayList<ArrayList<String>> rows, boolean showRowLabels) {

		this.rows = rows;
		TableModel tableModel = new TableModel(this.rows, showRowLabels);
		JTable wholeDSMTable = new JTable(tableModel);


		tableAttributeInit(wholeDSMTable);

        wholeDSMTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for( int i = 0 ; i < wholeDSMTable.getColumnCount() ; i++)
            wholeDSMTable.getColumnModel().getColumn(i).setPreferredWidth(25);
        if(showRowLabels == true)
            wholeDSMTable.getColumnModel().getColumn(0).setPreferredWidth(300);

		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(wholeDSMTable), BorderLayout.CENTER);
	}

	private void tableAttributeInit(JTable table) {
		// JTable Attributes
		int firstLine = 0;
		int rowHeight = 30;
		int fontSize = 15;
		int columnWidth = 100;
		JTableHeader header = table.getTableHeader();
		
		// set rowHeight
		table.setRowHeight(rowHeight);
		table.setFont(new Font("SansSerif", Font.PLAIN, fontSize));

		DefaultTableCellRenderer firstLineRender = new DefaultTableCellRenderer();
		DefaultTableCellRenderer restRender = new DefaultTableCellRenderer();

		firstLineRender.setBackground(Color.LIGHT_GRAY);
		firstLineRender.setHorizontalAlignment(JLabel.RIGHT);
		restRender.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(firstLine)
				.setCellRenderer(firstLineRender);
		header.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		table.getColumnModel().getColumn(firstLine).setPreferredWidth(columnWidth);
		
		for (int i = 1; i < table.getRowCount() + 1; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(restRender);
		}
		
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for( int i = 0 ; i < table.getColumnCount() ; i++)
            table.getColumnModel().getColumn(i).setPreferredWidth(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.setRowSelectionAllowed(false);
	}

	private class TableModel extends AbstractTableModel {

		private ArrayList<ArrayList<String>> tableData;
		private ArrayList columnIndex;
		private boolean showRowLabels;

		TableModel(ArrayList<ArrayList<String>> rows, boolean showRowLabels) {
			setShowRowLabels(showRowLabels);
			init(rows);
		}

		private void init(ArrayList<ArrayList<String>> rows) {

			tableData = rows;
			columnIndex = new ArrayList();

			columnIndex.add("");
			for (int i = 0; i < this.tableData.size(); i++) {
				String s = new String();
				s = (i + 1) + "";
				columnIndex.add(s);
				if (this.showRowLabels == true) {
					s = s + "."+ this.getValueAt(i, 0).toString();
				}
				tableData.get(i).set(0, s);
			}
		}

		public void setShowRowLabels(boolean state) {
			this.showRowLabels = state;

		}
		@Override
		public int getRowCount() {
			return tableData.size();
		}

		@Override
		public int getColumnCount() {
			return tableData.get(0).size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {

			String data = new String((String) tableData.get(rowIndex).get(
					columnIndex));
			int row, column;
			row = rowIndex + 1;
			column = columnIndex;
			if (row != 0 && column != 0) {
				if (row == column)
					return "·";

				switch (data) {
				case ("0"):
					return "";
				default:
					return "X";
				}
			} else
				return data;

		}

		public String getColumnName(int columnIndex) {
			return (String) this.columnIndex.get(columnIndex);
		}
	}

}
