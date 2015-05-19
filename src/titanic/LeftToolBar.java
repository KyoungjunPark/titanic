package titanic;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class LeftToolBar extends JToolBar{
	
	private JButton expandAllButton;
	private JButton collapseAllButton;
	private JButton groupButton;
	private JButton ungroupButton;
	private JButton moveUpButton;
	private JButton moveDownButton;
	private JButton deleteButton;
	
	public LeftToolBar()
	{
		
		setFloatable(false);
		
		ImageIcon expandAllIcon = new ImageIcon("util/expand.png");
		expandAllButton = new JButton(expandAllIcon);
		expandAllButton.setEnabled(false);
		add(expandAllButton);
		
		ImageIcon collapseAllIcon = new ImageIcon("util/collapse.png");
		collapseAllButton = new JButton(collapseAllIcon);
		collapseAllButton.setEnabled(false);
		add(collapseAllButton);
		
		ImageIcon groupIcon = new ImageIcon("util/group.png");
		groupButton = new JButton(groupIcon);
		groupButton.setEnabled(false);
		add(groupButton);
		
		ImageIcon ungroupIcon = new ImageIcon("util/ungroup.png");
		ungroupButton = new JButton(ungroupIcon);
		ungroupButton.setEnabled(false);
		add(ungroupButton);
		
		ImageIcon moveUpIcon = new ImageIcon("util/up.png");
		moveUpButton = new JButton(moveUpIcon);
		moveUpButton.setEnabled(false);
		add(moveUpButton);
		
		ImageIcon moveDownIcon = new ImageIcon("util/down.png");
		moveDownButton = new JButton(moveDownIcon);
		moveDownButton.setEnabled(false);
		add(moveDownButton);
		
		ImageIcon deleteIcon = new ImageIcon("util/delete.png");
		deleteButton = new JButton(deleteIcon);
		deleteButton.setEnabled(false);
		add(deleteButton);
		
	}
	
	public JButton getExpandAllButton() {
		return expandAllButton;
	}

	public JButton getCollapseAllButton() {
		return collapseAllButton;
	}

	public JButton getGroupButton() {
		return groupButton;
	}

	public JButton getUngroupButton() {
		return ungroupButton;
	}

	public JButton getMoveUpButton() {
		return moveUpButton;
	}

	public JButton getMoveDownButton() {
		return moveDownButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}
	
}
