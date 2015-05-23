package titanic;

import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import util.GroupNode;
import model.ModelManager;

public class FileTree extends JTree implements Controllerable {

	private DefaultMutableTreeNode root;

	public FileTree() {
		setModel(null);

	}

	public void makeTree() {
		ModelManager.sharedModelManager().setTitanicModelID(0);
		root = ModelManager.sharedModelManager().getCurrentTitanicModel()
				.getGroupNode().getTreeNode();
		setTreeTag();
		setModel(new DefaultTreeModel(root));
	}
	
	protected void expandAll(FileTree tree, int startingIndex, int rowCount) {
		for(int i=startingIndex; i<rowCount; i++){
	        tree.expandRow(i);
	    }

	    if(tree.getRowCount()!= rowCount){
	        expandAll(tree, rowCount, tree.getRowCount());
	    }
	}
	protected void collapseAll(FileTree tree, int startingIndex, int rowCount) {
		for(int i=rowCount-1; i>=startingIndex; i--) {
			tree.collapseRow(i);
		}

	}
	protected void delete(FileTree tree) {
		/*Integer selectedRowIndex[] = new Integer[tree.getSelectionRows().length];
		for(int i=0; i<selectedRowIndex.length; i++) {
			selectedRowIndex[i] = new Integer(tree.getSelectionRows()[i]);
		}*/
		
		for(int i=0; i<tree.getSelectionRows().length; i++) {
			tree.remove(tree.getSelectionRows()[i]);
		}
		
	}

	/*
	 * This function's purpose is to assign tags witch are differentiate a root
	 * node, sub root node, leaf node
	 */
	private void setTreeTag() {
		Enumeration nodeEnumeration = root.breadthFirstEnumeration();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodeEnumeration.nextElement();

		JOptionPane.showMessageDialog(null, root.getDepth());

	}

	public DefaultMutableTreeNode findNode(DefaultMutableTreeNode root,
			String search) {
		
		Enumeration nodeEnumeration = root.breadthFirstEnumeration();
		while (nodeEnumeration.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodeEnumeration
					.nextElement();
			String found = (String) node.getUserObject();
			if (search.equals(found)) {
				return node;
			}
		}
		return null;
	}

	
	@Override
	public void setAction(String title, ActionListener action) {

	}
}
