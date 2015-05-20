package controller;

import javax.swing.JPanel;

public class LeftPanelController extends CenterPanelController{
	
	private JPanel leftPanel;
	
	public LeftPanelController(){}
	public LeftPanelController(JPanel leftPanel){
		this.leftPanel = leftPanel;
		init();
		
	}
	private void init()
	{
	}
}
