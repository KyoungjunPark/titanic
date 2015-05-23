package titanic;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

public class WholeDSMTable extends JPanel {

	private ArrayList<ArrayList> rows;

	WholeDSMTable(ArrayList<ArrayList> rows) {
		init(rows);
	}

	private void init(ArrayList<ArrayList> rows) {

		this.rows = rows;
		TableModel tableModel = new TableModel(this.rows);
		JTable wholeDSMTable = new JTable(tableModel);

		tableDistributeInit(wholeDSMTable);
		this.add(wholeDSMTable);
	}

	private void tableDistributeInit(JTable table) {
		// JTable distributes

		int rowHeight = 50;
		table.setRowHeight(rowHeight);
		table.setFont(new Font("SansSerif", Font.PLAIN, 40));

		// wholeDSMTable.setColumnSelectionAllowed(true);
		// wholeDSMTable.setRowSelectionAllowed(true);
		// wholeDSMTable.setCellSelectionEnabled(true);
		// wholeDSMTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		DefaultTableCellRenderer firstLineRender = new DefaultTableCellRenderer();
		DefaultTableCellRenderer restRender = new DefaultTableCellRenderer();
		int firstLine = 0;
		firstLineRender.setBackground(Color.LIGHT_GRAY);
		table.getColumnModel().getColumn(firstLine).setCellRenderer(firstLineRender);
		table.getColumnModel().getColumn(firstLine).setPreferredWidth(250);
		table.setAutoscrolls(true);
		restRender.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 1; i < table.getRowCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(restRender);
		}

	}

	private class TableModel extends AbstractTableModel {

		private ArrayList<ArrayList> tableData;
		private ArrayList columnIndex;
		private boolean showRowLabels;

		TableModel(ArrayList<ArrayList> rows) {
			init(rows);
		}

		private void init(ArrayList<ArrayList> rows) {

			showRowLabels = false; // 임의 설정

			tableData = rows;
			columnIndex = new ArrayList();
			columnIndex.add("");
			for (int i = 0; i < this.tableData.size(); i++)
				columnIndex.add("" + (i + 1));
			tableData.add(0, columnIndex);

			for (int i = 1; i < this.tableData.size(); i++) {
				String s = new String();
				s = i + "";
				if (this.showRowLabels == true) {
					s = s + " " + this.getValueAt(i, 0).toString();
				}
				tableData.get(i).set(0, s);
			}
		}

		public void setShowRowLabels(boolean state) {
			showRowLabels = state;
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

			if (rowIndex != 0 && columnIndex != 0) {
				if (rowIndex == columnIndex)
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
	}

}
