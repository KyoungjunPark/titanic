package titanic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.xml.ws.Service;

import com.sun.javafx.sg.prism.NGShape;

import model.EventManager;
import model.ModelManager;
import model.SaveException;
import model.T3;

public class ContentsPanel extends JTabbedPane implements Controllerable {

	private ArrayList<RightPanel> contents;
	private ArrayList<T3> groupInfo;
	private ArrayList<ArrayList<String>> newData;
	private int tabIndex;
    private JButton closeButton;

	public ContentsPanel() {
		contents = new ArrayList<RightPanel>();
	}

	public void drawTableAtTab(int tabIndex) {

		// redraw를 하기 위해서 정보를 갱신한다.
		this.tabIndex=tabIndex;
		regetTableData();
		getGroupInfo();
        for(RightPanel panel : contents)
            if(panel.getID() == this.tabIndex)
                panel.redrawPanel(newData, groupInfo);

		String tabName = ModelManager.sharedModelManager().getCurrentTitanicModel()
				.getDsmModel().getFileName();

		repaint();

	}

	public int getRightPanelIndex(int id){
        for(int i = 0 ; i < this.getComponentCount() ; i++){
            if(((RightPanel)this.getComponentAt(i)).getID() == id) return i;

        }
        return Integer.parseInt(null);
    }

	protected void addRightPanel(final RightPanel panel) {
		JPanel tabPanel = new JPanel(new GridBagLayout());
		tabPanel.setOpaque(false);

		String fileName;
		if(ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFileName() == null){
			fileName = "untitle-" + contents.size();
		}
		else{
            fileName = ModelManager.sharedModelManager()
                    .getCurrentTitanicModel().getDsmModel().getFileName();
        }
        if(ModelManager.sharedModelManager().getCurrentTitanicModel().isEditModel()){
            fileName += "(Edit)";
        }

		JLabel title = new JLabel(fileName);

        ImageIcon icon = new ImageIcon("util/closeTab.png");
        closeButton  = new JButton(icon);
        closeButton.setName("closeButton");
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);

        setAction("closeButton", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ModelManager.sharedModelManager().getTitanicModel(panel.getID()).isEdit() && !ModelManager.sharedModelManager().getTitanicModel(panel.getID()).isEditModel()){
                    int selected = JOptionPane.showConfirmDialog(null, "Clustering has been modified, Save changes?", "Save changes?", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (selected == 0) { //yes
                        try {
                            if(ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().getFilePath() != null) {
                                ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().save();
                            }else if(ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFilePath() != null){
                                ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().save();
                            }else{
                            	
                                File openFile;

                                JFileChooser fc = new JFileChooser();
                                fc.setFileFilter(new FileFilter() {

                                    @Override
                                    public String getDescription() {
                                        return "CLSX File";
                                    }
                                    @Override
                                    public boolean accept(File f) {
                                        // TODO Auto-generated method stub
                                        return f.getName().endsWith(".clsx") || f.getName().endsWith(".dsm") || f.isDirectory();
                                    }
                                });
                                int yn = fc.showOpenDialog(null);
                                if (yn != JFileChooser.APPROVE_OPTION)
                                    return;

                                openFile = fc.getSelectedFile();
                                ModelManager.sharedModelManager().save(openFile.getPath());
                                
                                if(!openFile.getName().endsWith(".clsx") && !openFile.getName().endsWith(".dsm")){
                                    String fileDir = openFile.getPath() + ".clsx";
                                    openFile = new File(fileDir);
                                }
                            }
                        } catch (SaveException e1) {
                            e1.printStackTrace();
                        }
                    } else if (selected == 1) { //no
                    } else { // cancel
                        return;
                    }

                }
                //tab closure
                ModelManager.sharedModelManager().removeTitanicModel(panel.getID());
                removeTab(panel.getID());
                EventManager.callEvent("FileTree-redraw");
            }
        });
		GridBagConstraints gridBag = new GridBagConstraints();
		gridBag.gridx = 0;
		gridBag.gridy = 0;
		gridBag.weightx = 1;

		tabPanel.add(title,gridBag);
		gridBag.gridx++;
		gridBag.weightx = 0;
		tabPanel.add(closeButton,gridBag);

		this.addTab(fileName, null, panel, fileName);
        contents.add(panel);
		this.setTabComponentAt(this.getTabCount()-1, tabPanel);
	}

	protected void addRightPanel() {

		RightPanel panel = new RightPanel();
		contents.add(panel);
		this.add(panel, ModelManager.sharedModelManager()
				.getCurrentTitanicModel().getDsmModel().getFileName());
	}

	@Override
	public void setAction(String title, ActionListener action) {
        if(closeButton.getName().compareTo(title) == 0){
            closeButton.addActionListener(action);
        }
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
        String tabName;

        if(ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().getFilePath() == null) {
            tabName = ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFileName();
        }
        else {
            tabName = ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().getFileName();
        }
        JPanel panel = (JPanel)this.getTabComponentAt(currentIndex);

        for(Component component : panel.getComponents()){
            if(component instanceof JLabel){
                ((JLabel)component).setText(tabName);
                break;
            }
        }
    }
    private void removeTab(int ID){
        for(RightPanel panel : contents){
            if(panel.getID() == ID){
                this.remove(panel);
                contents.remove(panel);
                break;
            }
        }
    }
}
