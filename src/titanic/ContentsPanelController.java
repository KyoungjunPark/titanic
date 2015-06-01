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


                for(int i = 0 ; i<sourceTabbedPane.getTabCount() ; i++){
                    if(ModelManager.sharedModelManager().getCurrentID() == ((RightPanel)sourceTabbedPane.getTabComponentAt(i)).getID()) {
                       System.out.println( ((JButton)((JPanel)sourceTabbedPane.getTabComponentAt(i)).getComponent(1)).getName());
                    }
                }

                if(ModelManager.sharedModelManager().getTitanicModelCount() == 0){
                    EventManager.callEvent("InitialStatus");
                }else if(ModelManager.sharedModelManager().getCurrentTitanicModel().isEditModel()) {
                    EventManager.callEvent("FileTree-redraw");
                    EventManager.callEvent("Redraw-Table");
                    EventManager.callEvent("EditStatus");
                }else{
                    EventManager.callEvent("FileTree-redraw");
                    EventManager.callEvent("Redraw-Table");
                    EventManager.callEvent("CLSXStatus");
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
