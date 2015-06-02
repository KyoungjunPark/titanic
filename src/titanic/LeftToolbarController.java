package titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.EventManager;
import model.ModelManager;
import titanic.LeftPanel.LeftToolBar;


public class LeftToolbarController extends LeftPanelController{

	private LeftToolBar leftToolbar;
	
	public LeftToolbarController(LeftToolBar leftToolbar){
		this.leftToolbar = leftToolbar;
		setEvent();
		init();
	}
	
	private void setEvent()
	{
		
	}
	private void init()
	{
		leftToolbar.setAction("Expand All", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventManager.callEvent("expandAll");
			}
		});
		leftToolbar.setAction("Collapse All", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventManager.callEvent("collapseAll");
				
			}
		});
		leftToolbar.setAction("Group", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventManager.callEvent("group");
				EventManager.callEvent("Redraw-Table");
			}
		});
		leftToolbar.setAction("Ungroup", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventManager.callEvent("ungroup");
				EventManager.callEvent("Redraw-Table");
				
			}
		});
		leftToolbar.setAction("Move Up", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventManager.callEvent("moveUp");
				EventManager.callEvent("Redraw-Table");
				
			}
		});
		leftToolbar.setAction("Move Down", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventManager.callEvent("moveDown");
				EventManager.callEvent("Redraw-Table");
			}
		});
		leftToolbar.setAction("Delete", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventManager.callEvent("delete");
				EventManager.callEvent("Redraw-Table");
			}
		});

		leftToolbar.setAction("New DSM Row", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventManager.callEvent("newDSMRow");
				EventManager.callEvent("Redraw-Table");
				EventManager.callEvent("FileTree-redraw");
			}
		});

		leftToolbar.setAction("Yassine", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().sortYassine();
			}
		});
		
	}
	
	protected void expandAllButtonEnable(){
		leftToolbar.expandAllButtonEnable();
	}
	protected void expandAllButtonDisable(){
		leftToolbar.expandAllButtonDisable();
	}
	protected void collapseAllButtonEnable(){
		leftToolbar.collapseAllButtonEnable();
	}
	protected void collapseAllButtonDisable(){
		leftToolbar.collapseAllButtonDisable();
	}
	protected void groupButtonEnable(){
		leftToolbar.groupButtonEnable();
	}
	protected void groupButtonDisable(){
		leftToolbar.groupButtonDisable();
	}
	protected void ungroupButtonEnable(){
		leftToolbar.ungroupButtonEnable();
	}
	protected void ungroupButtonDisable(){
		leftToolbar.ungroupButtonDisable();
	}
	protected void moveUpButtonEnable(){
		leftToolbar.moveUpButtonEnable();
	}
	protected void moveUpButtonDisable(){
		leftToolbar.moveUpButtonDisable();
	}
	protected void moveDownButtonEnable(){
		leftToolbar.moveDownButtonEnable();
	}
	protected void moveDownButtonDisable(){
		leftToolbar.moveDownButtonDisable();
	}
	protected void deleteButtonEnable(){
		leftToolbar.deleteButtonEnable();
	}
	protected void deleteButtonDisable(){
		leftToolbar.deleteButtonDisable();
	}
	protected void newDSMRowButtonEnable(){
		leftToolbar.newDSMRowButtonEnable();
	}
	protected void newDSMRowButtonDisable(){
		leftToolbar.newDSMRowButtonDisable();
	}
    protected void yassineButtonEnable(){leftToolbar.yassineButtonEnable();}
    protected void yassineButtonDisable(){leftToolbar.yassineButtonDisable();}
}
