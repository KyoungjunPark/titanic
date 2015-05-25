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

	private ArrayList<ArrayList<String>> newData;
	private int tabIndex;

	public ContentsPanel() {
		contents = new ArrayList<RightPanel>();


	}

	public void drawTableAtTab(int tabIndex) {

		// redraw를 하기 위해서 정보를 갱신한다.
		regetTableData(tabIndex);
		contents.get(this.tabIndex - 1).setTableData(newData);
		contents.get(this.tabIndex - 1).redrawPanel(newData);

		// 아직 탭이 없으면 추가해주고, 아니라면 repaint만 해준다.
		if (tabIndex == 0)
			this.addTab("" + (this.tabIndex), contents.get(this.tabIndex - 1));
		else {
			/*
			this.removeAll();
			this.addTab("" + (this.tabIndex), contents.get(this.tabIndex - 1));
			*/
			repaint();
		}
	}

	public ArrayList<RightPanel> getRightPanel() {
		return contents;
	}

	@Override
	public void setAction(String title, ActionListener action) {

	}

	// 정보 갱신
	public void regetTableData(int tabIndex) {
		// 맨 처음 탭을 위한 숫자 조정
		if (tabIndex == 0)
			this.tabIndex = 1;
		else
			this.tabIndex = tabIndex;

		// 요기서 다시 받아옴
		newData = new ArrayList<ArrayList<String>>();
		newData = ModelManager.sharedModelManager().getCurrentTitanicModel()
				.getMatrixData();
	}

	public void setShowRowLabels(boolean state, int tabIndex) {
		if (tabIndex == 0) {
			contents.get(tabIndex).setShowRowLabels(state);
		}
		else
			contents.get(tabIndex-1).setShowRowLabels(state);
	}
}
