package titanic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class LeftPanel extends JPanel implements Controllerable {

	private LeftToolBar toolbar;
	private FileTree fileTree;

	public LeftPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		// leftToolbar
		toolbar = new LeftToolBar();
		this.add(toolbar, BorderLayout.NORTH);

		// Jtree
		fileTree = new FileTree();
		this.add(new JScrollPane(fileTree), BorderLayout.CENTER);

	}

	public FileTree getfileTree() {
		return fileTree;
	}

	public LeftToolBar getToolbar() {
		return toolbar;
	}

	public class LeftToolBar extends JToolBar implements Controllerable {

		private JButton expandAllButton;
		private JButton collapseAllButton;
		private JButton groupButton;
		private JButton ungroupButton;
		private JButton moveUpButton;
		private JButton moveDownButton;
		private JButton deleteButton;
		private JButton newDSMRowButton;

		public LeftToolBar() {

			setFloatable(false);

			ImageIcon expandAllIcon = new ImageIcon("util/expand.png");
			expandAllButton = new JButton(expandAllIcon);
			expandAllButton.setName("Expand All");
			expandAllButton.setToolTipText("Expand All");
			expandAllButton.setEnabled(false);
			add(expandAllButton);

			ImageIcon collapseAllIcon = new ImageIcon("util/collapse.png");
			collapseAllButton = new JButton(collapseAllIcon);
			collapseAllButton.setName("Collapse All");
			collapseAllButton.setToolTipText("Collapse All");
			collapseAllButton.setEnabled(false);
			add(collapseAllButton);

			ImageIcon groupIcon = new ImageIcon("util/group.png");
			groupButton = new JButton(groupIcon);
			groupButton.setName("Group");
			groupButton.setToolTipText("Group");
			groupButton.setEnabled(false);
			add(groupButton);

			ImageIcon ungroupIcon = new ImageIcon("util/ungroup.png");
			ungroupButton = new JButton(ungroupIcon);
			ungroupButton.setName("Ungroup");
			ungroupButton.setToolTipText("Ungroup");
			ungroupButton.setEnabled(false);
			add(ungroupButton);

			ImageIcon moveUpIcon = new ImageIcon("util/up.png");
			moveUpButton = new JButton(moveUpIcon);
			moveUpButton.setName("Move Up");
			moveUpButton.setToolTipText("Move Up");
			moveUpButton.setEnabled(false);
			add(moveUpButton);

			ImageIcon moveDownIcon = new ImageIcon("util/down.png");
			moveDownButton = new JButton(moveDownIcon);
			moveDownButton.setName("Move Down");
			moveDownButton.setToolTipText("Move Down");
			moveDownButton.setEnabled(false);
			add(moveDownButton);

			ImageIcon deleteIcon = new ImageIcon("util/delete.png");
			deleteButton = new JButton(deleteIcon);
			deleteButton.setName("Delete");
			deleteButton.setToolTipText("Delete");
			deleteButton.setEnabled(false);
			add(deleteButton);
			
			ImageIcon newDSMRowIcon = new ImageIcon("util/add.png");
			newDSMRowButton = new JButton(newDSMRowIcon);
			newDSMRowButton.setName("New DSM Row");
			newDSMRowButton.setToolTipText("New DSM Row");
			newDSMRowButton.setEnabled(false);
			add(newDSMRowButton);

		}
		protected void expandAllButtonEnable(){
			this.expandAllButton.setEnabled(true);
		}
		protected void expandAllButtonDisable(){
			this.expandAllButton.setEnabled(false);
		}
		protected void collapseAllButtonEnable(){
			this.collapseAllButton.setEnabled(true);
		}
		protected void collapseAllButtonDisable(){
			this.collapseAllButton.setEnabled(false);
		}
		protected void groupButtonEnable(){
			this.groupButton.setEnabled(true);
		}
		protected void groupButtonDisable(){
			this.groupButton.setEnabled(false);
		}
		protected void ungroupButtonEnable(){
			this.ungroupButton.setEnabled(true);
		}
		protected void ungroupButtonDisable(){
			this.ungroupButton.setEnabled(false);
		}
		protected void moveUpButtonEnable(){
			this.moveUpButton.setEnabled(true);
		}
		protected void moveUpButtonDisable(){
			this.moveUpButton.setEnabled(false);
		}
		protected void moveDownButtonEnable(){
			this.moveDownButton.setEnabled(true);
		}
		protected void moveDownButtonDisable(){
			this.moveDownButton.setEnabled(false);
		}
		protected void deleteButtonEnable(){
			this.deleteButton.setEnabled(true);
		}
		protected void deleteButtonDisable(){
			this.deleteButton.setEnabled(false);
		}
		protected void newDSMRowButtonEnable(){
			this.newDSMRowButton.setEnabled(true);
		}
		protected void newDSMRowButtonDisable(){
			this.newDSMRowButton.setEnabled(false);
		}
		
		protected void changeDSMStatus() {
			expandAllButton.setEnabled(true);
			collapseAllButton.setEnabled(true);
			groupButton.setEnabled(false);
			ungroupButton.setEnabled(false);
			moveUpButton.setEnabled(false);
			moveDownButton.setEnabled(false);
			deleteButton.setEnabled(false);

		}
		protected void changeRootStatus() {
			expandAllButton.setEnabled(true);
			collapseAllButton.setEnabled(true);
			groupButton.setEnabled(false);
			ungroupButton.setEnabled(false);
			moveUpButton.setEnabled(false);
			moveDownButton.setEnabled(false);
			deleteButton.setEnabled(false);

		}
		protected void changeItemStatus() {
			expandAllButton.setEnabled(true);
			collapseAllButton.setEnabled(true);
			groupButton.setEnabled(true);
			ungroupButton.setEnabled(false);
			moveUpButton.setEnabled(true);
			moveDownButton.setEnabled(true);
			deleteButton.setEnabled(true);

		}
		protected void changeItemStatusTop() {
			expandAllButton.setEnabled(true);
			collapseAllButton.setEnabled(true);
			groupButton.setEnabled(true);
			ungroupButton.setEnabled(false);
			moveUpButton.setEnabled(false);
			moveDownButton.setEnabled(true);
			deleteButton.setEnabled(true);

		}
		protected void changeItemStatusBottom() {
			expandAllButton.setEnabled(true);
			collapseAllButton.setEnabled(true);
			groupButton.setEnabled(true);
			ungroupButton.setEnabled(false);
			moveUpButton.setEnabled(true);
			moveDownButton.setEnabled(false);
			deleteButton.setEnabled(true);
		}
		protected void changeSubRootStatus() {
			expandAllButton.setEnabled(true);
			collapseAllButton.setEnabled(true);
			groupButton.setEnabled(true);
			ungroupButton.setEnabled(true);
			moveUpButton.setEnabled(true);
			moveDownButton.setEnabled(true);
			deleteButton.setEnabled(true);
		}
		protected void changeSubRootStatusTop() {
			expandAllButton.setEnabled(true);
			collapseAllButton.setEnabled(true);
			groupButton.setEnabled(true);
			ungroupButton.setEnabled(true);
			moveUpButton.setEnabled(false);
			moveDownButton.setEnabled(true);
			deleteButton.setEnabled(true);
		}
		protected void changeSubRootStatusBottom() {
			expandAllButton.setEnabled(true);
			collapseAllButton.setEnabled(true);
			groupButton.setEnabled(true);
			ungroupButton.setEnabled(true);
			moveUpButton.setEnabled(true);
			moveDownButton.setEnabled(false);
			deleteButton.setEnabled(true);
		}

		@Override
		public void setAction(String title, ActionListener action) {
			for (Component component : this.getComponents()) {
				if (component instanceof JButton
						&& ((JButton) component).getName().compareTo(title) == 0)
					((JButton) component).addActionListener(action);
			}

		}
	}

	@Override
	public void setAction(String title, ActionListener action) {
		JOptionPane.showMessageDialog(null, this.getComponentCount());
		for (Component component : this.getComponents()) {
			for (Component item : ((JMenu) component).getMenuComponents()) {
				if (item instanceof JMenuItem
						&& ((JMenuItem) item).getText().compareTo(title) == 0)
					((JMenuItem) item).addActionListener(action);
			}
		}

	}

}
