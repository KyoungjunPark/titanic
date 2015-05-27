package titanic;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import model.ModelManager;
import model.T3;

public class ContentsPanel extends JTabbedPane implements Controllerable {

	private ArrayList<RightPanel> contents;
	private ArrayList<T3> groupInfo;
	private ArrayList<ArrayList<String>> newData;
	private int tabIndex;

	public ContentsPanel() {
		contents = new ArrayList<RightPanel>();
	}

	public void drawTableAtTab(int tabIndex) {

		// redraw를 하기 위해서 정보를 갱신한다.
		this.tabIndex=tabIndex;
		regetTableData();
		getGroupInfo();
		//	contents.get(this.tabIndex).setTableData(newData);
        for(RightPanel panel : contents)
            if(panel.getID() == this.tabIndex)
                panel.redrawPanel(newData, groupInfo);

		String tabName = new String();

		tabName = ModelManager.sharedModelManager().getCurrentTitanicModel()
				.getDsmModel().getFileName();

		repaint();

	}

	public int getRightPanelIndex(int id){
        for(int i = 0 ; i < this.getComponentCount() ; i++){
            if(((RightPanel)this.getComponentAt(i)).getID() == id) return i;

        }
        return Integer.parseInt(null);
    }

	protected void addRightPanel(RightPanel panel) {

		contents.add(panel);
		this.add(panel, ModelManager.sharedModelManager()
				.getCurrentTitanicModel().getDsmModel().getFileName());
	}

	protected void addRightPanel() {

		RightPanel panel = new RightPanel();
		contents.add(panel);
		this.add(panel, ModelManager.sharedModelManager()
				.getCurrentTitanicModel().getDsmModel().getFileName());
	}

	@Override
	public void setAction(String title, ActionListener action) {

	}

	// 정보 갱신
	public void regetTableData() {
	///	this.tabIndex = tabIndex;
		
		// 요기서 다시 받아옴
		newData = new ArrayList<ArrayList<String>>();
		try {
			newData = ModelManager.sharedModelManager()
					.getCurrentTitanicModel().getMatrixData();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	public void getGroupInfo(){
		
		groupInfo = new ArrayList<T3>();

		try{
			groupInfo = ModelManager.sharedModelManager()
					.getCurrentTitanicModel().getGroupData();
			
		}catch(NullPointerException e){e.printStackTrace();}
		
		
	}
	public void setShowRowLabels(boolean state, int tabIndex) {
            for( RightPanel panel: contents)
                if(panel.getID() == tabIndex)
                    panel.setShowRowLabels(state);
	}

	/*
	 refrechTabName은 clsx파일이 있을 경우 해당 이름을 tab으로 설정하고 그 외는 dsm파일의 이름으로 tab이름을 설정합니다.
	 */
    public void refreshTabName() {


        int currentIndex = this.getRightPanelIndex(ModelManager.sharedModelManager().getCurrentID());
        if(ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel() == null) {
            this.setTitleAt(currentIndex, ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFileName());
        }
        else {
            this.setTitleAt(currentIndex, ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().getFileName());
        }
    }
}
