package titanic;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionListener;

public class LeftPanel extends JPanel implements Controllerable{
	
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
		
	}

	@Override
	public void setAction(String title, ActionListener action) {
		JOptionPane.showMessageDialog(null, this.getComponentCount());
        for(Component component : this.getComponents()){
        	for(Component item : ((JMenu)component).getMenuComponents()){
	            if( item instanceof JMenuItem && ((JMenuItem) item).getText().compareTo(title) == 0)
	                ((JMenuItem) item).addActionListener(action);
        	}
        }
		
	}

}
