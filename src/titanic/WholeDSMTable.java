package titanic;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

public class WholeDSMTable extends JPanel {

	/**
	 * DSM 정보를 받아와서 화면에 표시할 테이블을 만든다. DSM 정보는 ArrayList<ArrayList>의 형식이며
	 * list.get(i)는 name, dependency 의 집합 이다.
	 */
	private ArrayList<ArrayList<String>> rows;

	WholeDSMTable(ArrayList<ArrayList<String>> rows) throws NullPointerException {
			init(rows);
	}

	private void init(ArrayList<ArrayList<String>> rows) {

		this.rows = rows;
		TableModel tableModel = new TableModel(this.rows);
		JTable wholeDSMTable = new JTable(tableModel);

		tableDistributeInit(wholeDSMTable);

		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(wholeDSMTable), BorderLayout.CENTER);

	}

	private void tableDistributeInit(JTable table) {
		// JTable distributes
		int columnWidth = 100;
		int firstLine = 0;
		int rowHeight = 30;
		int fontSize = 20;
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
		table.getColumnModel().getColumn(firstLine)
				.setPreferredWidth(columnWidth);
		for (int i = 1; i < table.getRowCount() + 1; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(restRender);
		}

	}

	private class TableModel extends AbstractTableModel {

		private ArrayList<ArrayList<String>> tableData;
		private ArrayList columnIndex;
		private boolean showRowLabels;

		TableModel(ArrayList<ArrayList<String>> rows) {
			init(rows);
		}

		private void init(ArrayList<ArrayList<String>> rows) {

			// 임의로 설정된 상태임 setShowRowLabels(boolean) 이용해서 설정해줘야 함
			// 삭제할 것
			showRowLabels = false;

			tableData = rows;
			columnIndex = new ArrayList();

			columnIndex.add("");
			for (int i = 0; i < this.tableData.size(); i++) {
				String s = new String();
				s = (i + 1) + "";
				columnIndex.add(s);
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

