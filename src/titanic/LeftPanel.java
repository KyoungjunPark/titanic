package titanic;

import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.Color;

public class LeftPanel extends JPanel {
	
	private LeftToolBar toolbar;
	
	public LeftPanel(){
		setBackground(Color.WHITE);
		
		setLayout(new BorderLayout(0,0));
		
		
		//leftToolbar
		toolbar = new LeftToolBar();
		this.add(toolbar, BorderLayout.NORTH);
	}

	public LeftToolBar getToolbar() {
		return toolbar;
	}

	

}
