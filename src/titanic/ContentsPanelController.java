package titanic;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.EventManager;
import model.ModelManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentsPanelController extends CenterPanelController {

    private ContentsPanel contentsPanel;

    public ContentsPanelController() {
    }

    public ContentsPanelController(ContentsPanel contentsPanel) {
        this.contentsPanel = contentsPanel;
        setControllers();
        setEvent();
    }

    private void setControllers() {
        contentsPanel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                if( sourceTabbedPane.getSelectedComponent() != null)
                    ModelManager.sharedModelManager().setCurrentID(((RightPanel) sourceTabbedPane.getSelectedComponent()).getID());
                if(ModelManager.sharedModelManager().getTitanicModelCount() == 0){
                    EventManager.callEvent("InitialStatus");
                }else if(ModelManager.sharedModelManager().getCurrentTitanicModel().isEditModel()) {
                    EventManager.callEvent("FileTree-redraw");
                    EventManager.callEvent("Redraw-Table");
                    EventManager.callEvent("EditStatus");
                    EventManager.callEvent("yassineButtonDisable");
                }else if(ModelManager.sharedModelManager().getCurrentTitanicModel().isEdit()){
                    EventManager.callEvent("FileTree-redraw");
                    EventManager.callEvent("Redraw-Table");
                    EventManager.callEvent("CLSXStatus");
                    EventManager.callEvent("yassineButtonDisable");
                }else{
                    EventManager.callEvent("FileTree-redraw");
                    EventManager.callEvent("Redraw-Table");
                    EventManager.callEvent("CLSXStatus");
                    EventManager.callEvent("yassineButtonEnable");
                }

            }
        });

    }

    private void setEvent() {

    }

    protected void addRightPanel() {
        int id = ModelManager.sharedModelManager().getCurrentID();
        RightPanel panel = new RightPanel(id);
        contentsPanel.addRightPanel(panel);
        contentsPanel.setSelectedComponent(panel);
    }

    protected void redrawPanel() {
        contentsPanel.drawTableAtTab(ModelManager.sharedModelManager().getCurrentID());
    }


    public void setShowRowLabels(boolean state, int tabIndex) {
        contentsPanel.setShowRowLabels(state, tabIndex);
    }

    public void refreshTabName() {
        contentsPanel.refreshTabName();
    }

}
