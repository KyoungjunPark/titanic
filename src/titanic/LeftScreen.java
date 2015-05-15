package titanic;

import java.awt.BorderLayout;

import javax.swing.*;

public class LeftScreen extends JPanel {
	
	
	public LeftScreen(){
		
		setLayout(new BorderLayout(0,0));
		makeToolbar();
	}
	protected boolean makeToolbar()
	{
	
		
		//left side ToolBar
		JToolBar toolBar2 = new JToolBar();
		toolBar2.setFloatable(false);
		
		ImageIcon expandAllIcon = new ImageIcon(LeftScreen.class.getResource("").getPath()+"../../util/expand.png");
		JButton expandAllButton = new JButton(expandAllIcon);
		toolBar2.add(expandAllButton);
		
		ImageIcon collapseAllIcon = new ImageIcon(LeftScreen.class.getResource("").getPath()+"../../util/collapse.png");
		JButton collapseAllButton = new JButton(collapseAllIcon);
		toolBar2.add(collapseAllButton);
		
		ImageIcon groupIcon = new ImageIcon(LeftScreen.class.getResource("").getPath()+"../../util/group.png");
		JButton groupButton = new JButton(groupIcon);
		toolBar2.add(groupButton);
		
		ImageIcon ungroupIcon = new ImageIcon(LeftScreen.class.getResource("").getPath()+"../../util/ungroup.png");
		JButton ungroupButton = new JButton(ungroupIcon);
		toolBar2.add(ungroupButton);
		
		ImageIcon moveUpIcon = new ImageIcon(LeftScreen.class.getResource("").getPath()+"../../util/up.png");
		JButton moveUpButton = new JButton(moveUpIcon);
		toolBar2.add(moveUpButton);
		
		ImageIcon moveDownIcon = new ImageIcon(LeftScreen.class.getResource("").getPath()+"../../util/down.png");
		JButton moveDownButton = new JButton(moveDownIcon);
		toolBar2.add(moveDownButton);
		
		ImageIcon deleteIcon = new ImageIcon(LeftScreen.class.getResource("").getPath()+"../../util/delete.png");
		JButton deleteButton = new JButton(deleteIcon);
		toolBar2.add(deleteButton);
		
		this.add(toolBar2, BorderLayout.NORTH);
		return true;
		
	}

}
