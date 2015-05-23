package titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JOptionPane;


import titanic.LeftPanel.LeftToolBar;


public class LeftToolbarController extends LeftPanelController{

	private LeftToolBar leftToolbar;
	
	public LeftToolbarController(LeftToolBar leftToolbar){
		this.leftToolbar = leftToolbar;
		
		init();
	}
	
	private void init()
	{
		leftToolbar.setAction("Expand All", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Expand All is clicked");
				expandTree();
				//callEvent("expand all");
			}
		});
		leftToolbar.setAction("Collapse All", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Collapse All is clicked");
				
			}
		});
		leftToolbar.setAction("Group", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Group is clicked");
				
			}
		});
		leftToolbar.setAction("Ungroup", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ungroup is clicked");
				
			}
		});
		leftToolbar.setAction("Move Up", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Move Up is clicked");
				
			}
		});
		leftToolbar.setAction("Move Down", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Move Down is clicked");
				
			}
		});
		leftToolbar.setAction("Delete", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Delete is clicked");
				
			}
		});
		
		
	}
	
	protected void changeDSMStatus()
	{
		leftToolbar.changeDSMStatus();
		
	}
}
