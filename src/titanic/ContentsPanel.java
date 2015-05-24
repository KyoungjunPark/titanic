package titanic;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import model.ModelManager;

public class ContentsPanel extends JTabbedPane implements Controllerable {

	private ArrayList<RightPanel> contents;
	private RightPanel firstPanel;
	private int tabIndex = 0;
	ArrayList<ArrayList<String>> newData;

	public ContentsPanel() {
		contents = new ArrayList<RightPanel>();

		/*
		 * tab feature test JLabel mainLabel; JPanel mainPanel;
		 * 
		 * mainLabel = new JLabel("first page", SwingConstants.CENTER);
		 * mainPanel = new JPanel(); mainPanel.add(mainLabel);
		 * addTab("1",mainPanel);
		 */

	}

	public void drawTableAtTab(int tabIndex) {

		regetTableData(tabIndex);

		contents.get(this.tabIndex-1).setTableData(newData);
		contents.get(this.tabIndex-1).makeTable();

		if (tabIndex == 0)
			this.addTab("" + (this.tabIndex), contents.get(this.tabIndex-1));
		else{
			repaint();
		}
	}

	@Override
	public void setAction(String title, ActionListener action) {

	}

	public void regetTableData(int tabIndex) {

		newData = new ArrayList<ArrayList<String>>();

		if (tabIndex == 0) {
			this.tabIndex = 1;
			firstPanel = new RightPanel();
			contents.add(firstPanel);
		} else
			this.tabIndex = tabIndex;

		newData = ModelManager.sharedModelManager().getCurrentTitanicModel()
				.getMatrixData();
		contents.get(this.tabIndex - 1).setTableData(newData);
	}
}
