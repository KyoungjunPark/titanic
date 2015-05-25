package titanic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

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

		String tabName = new String();
		
		tabName = ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFileName(); 
		
		repaint();

	}

	public ArrayList<RightPanel> getRightPanel() {
		return contents;
	}

	protected void addRightPanel(RightPanel panel) {
		
		contents.add(panel);
		this.add(new JScrollPane(panel),ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFileName());
	}

	protected void addRightPanel() {
		
		RightPanel panel = new RightPanel();
		contents.add(panel);
		this.add(new JScrollPane(panel),ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFileName());
	}

	@Override
	public void setAction(String title, ActionListener action) {

	}

	// 정보 갱신
	public void regetTableData(int tabIndex) {
		this.tabIndex = tabIndex;
		// 요기서 다시 받아옴
		newData = new ArrayList<ArrayList<String>>();
		newData = ModelManager.sharedModelManager().getCurrentTitanicModel()
				.getMatrixData();
	}

	public void setShowRowLabels(boolean state, int tabIndex) {
		
			contents.get(tabIndex - 1).setShowRowLabels(state);
	}
}
