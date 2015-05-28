package titanic;

import model.T3;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import util.GroupNode;
import util.Node;

public class DependencyTable extends JPanel {

	/**
	 * DSM 정보를 받아와서 화면에 표시할 테이블을 만듭니다. DSM 정보는 ArrayList<ArrayList<String>>의
	 * 형식이며 ArrayList<String>은 [name , 0, 1, other dependencies...]의 형식의 데이터를
	 * 저장하고 있습니다.
	 */
	private ArrayList<ArrayList<String>> rows;
	private ArrayList<String> rowNames;
	private Hashtable<String, Color> colorInfo;


	DependencyTable(ArrayList<ArrayList<String>> rows, ArrayList<T3> groupInfo,
			boolean showRowLabels) throws NullPointerException {

		init(rows, groupInfo, showRowLabels);
	}

	private void init(ArrayList<ArrayList<String>> rows,
			ArrayList<T3> groupInfo, boolean showRowLabels) {

		this.rows = rows;
		TableModel tableModel = new TableModel(this.rows, showRowLabels);

		/*********************************************************************************************/

		colorInfo = new Hashtable<String, Color>();
		for (int i = 0; i < groupInfo.size(); i++) {
			int first = groupInfo.get(i).getFirst() - 1;
			int last = groupInfo.get(i).getLast() - 1;

			for (int j = first; j < last + 1; j++) {
				for (int k = first; k < last + 1; k++) {
					colorInfo.put(j + ":" + k, levelColor(groupInfo.get(i)
							.getDepth()));
				}
			}
		}

		JTable rightTable = new JTable(tableModel) {
			public Component prepareRenderer(TableCellRenderer tcr, int row,
					int column) {
				Component c = super.prepareRenderer(tcr, row, column);

				if (colorInfo.containsKey((row - 1) + ":" + (column - 1)) == true)
					c.setBackground(colorInfo.get((row - 1) + ":"
							+ (column - 1)));
				else
					c.setBackground(levelColor(0));
				return c;
			}

			public String getToolTipText(MouseEvent e) {
				String tip;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);

				if (getValueAt(rowIndex, colIndex).toString().equals("X")) {
					tip = (rowIndex + 1) + "." + rowNames.get(rowIndex) + " → "
							+ (colIndex + 1) + "." + rowNames.get(colIndex);
					return tip;
				} else
					return null;
			}
		};

		rightTable.setAutoCreateRowSorter(true);
		rightTable.removeColumn(rightTable.getColumnModel().getColumn(0));
		tableAttributeInit(rightTable);
		JScrollPane sp = new JScrollPane(rightTable,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JTable leftTable = new JTable(tableModel);
		leftTable.setRowSorter(rightTable.getRowSorter());
		leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int x = leftTable.getColumnCount() - 1; x > 0; x--)
			leftTable.removeColumn(leftTable.getColumnModel().getColumn(x));

		tableAttributeInit(leftTable);

		if (showRowLabels == false)
			leftTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		else
			leftTable.getColumnModel().getColumn(0).setPreferredWidth(500);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
		renderer.setBackground(Color.LIGHT_GRAY);
		leftTable.getColumnModel().getColumn(0).setCellRenderer(renderer);

		JScrollPane spLeft = new JScrollPane(leftTable,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Dimension dim = spLeft.getPreferredSize();
		spLeft.setPreferredSize(new Dimension((showRowLabels ? 500 : 30),
				dim.height));
		sp.getVerticalScrollBar().setModel(
				spLeft.getVerticalScrollBar().getModel());

		JPanel p = new JPanel(new BorderLayout());
		p.add(sp);
		p.add(spLeft, BorderLayout.WEST);

		JScrollBar sb = new JScrollBar(SwingConstants.HORIZONTAL);
		sb.setModel(sp.getHorizontalScrollBar().getModel());

		this.setLayout(new BorderLayout());
		this.add(p);
		this.add(sb, BorderLayout.SOUTH);
	}

	private void tableAttributeInit(JTable table) {
		// JTable Attributes
		int rowHeight = 30;
		int fontSize = 15;
		JTableHeader header = table.getTableHeader();

		// set rowHeight
		table.setRowHeight(rowHeight);
		table.setFont(new Font("SansSerif", Font.PLAIN, fontSize));

		DefaultTableCellRenderer restRender = new DefaultTableCellRenderer();

		restRender.setHorizontalAlignment(JLabel.CENTER);

		header.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		table.getTableHeader().setReorderingAllowed(false);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(restRender);
		}

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < table.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(25);
		table.setRowSelectionAllowed(false);
	}

	// 높이에 따른 색
	private Color levelColor(int depth) {

		switch (depth % 5) {
		
		  case (0): return new Color(255, 255, 255); case (1): return new
		  Color(255, 202, 0); case (2): return new Color(98, 12, 172); case
		  (4): return new Color(15, 79, 168); case (3): return new Color(255,
		  116, 0); default: return new Color(255, 255, 255);
		 /*
		case (0):
			return new Color(255, 255, 255, 80);
		case (1):
			return new Color(255, 202, 0, 80);
		case (2):
			return new Color(98, 12, 172, 80);
		case (3):
			return new Color(255, 116, 0, 80);
		case (4):
			return new Color(15, 79, 168, 80);
		
		default:
			return new Color(255, 255, 255, 80);
*/
		}
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
			rowNames = new ArrayList<String>();
			columnIndex.add("");
			for (int i = 0; i < this.tableData.size(); i++) {
				String s = new String();
				s = (i + 1) + "";
				columnIndex.add(s);
				rowNames.add(this.getValueAt(i, 0).toString());
				if (this.showRowLabels == true) {
					s = s + "." + this.getValueAt(i, 0).toString();
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

				if (data.compareTo("0") == 0) {
					return "";
				} else {
					return "X";
				}

			} else
				return data;

		}

		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return " ";
			}
			return (String) this.columnIndex.get(columnIndex);
		}
	}

}
