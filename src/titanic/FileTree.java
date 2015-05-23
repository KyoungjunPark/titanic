package titanic;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import util.GroupNode;
import util.TreeNode;
import model.ModelManager;

public class FileTree extends JTree implements Controllerable {
	private TreeNode root;

	public FileTree() {
		setModel(null);

	}

	public void makeTree() {
		ModelManager.sharedModelManager().setTitanicModelID(0);
		root = ModelManager.sharedModelManager().getCurrentTitanicModel()
				.getGroupNode().getTreeNode();
		setModel(new DefaultTreeModel(root));
	}
	protected void moveUp()
	{
		ArrayList<DefaultMutableTreeNode> nodes = getSelectedNodes();
		
		for(int i = 0 ; i <nodes.size(); i++){
			DefaultMutableTreeNode node = nodes.get(i);
			if(node.getPreviousLeaf() == null){
				
				
			}
			//((DefaultTreeModel)this.getModel()).
			
		}
		
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
	protected void delete() {
		
		ArrayList<DefaultMutableTreeNode> nodes = getSelectedNodes();
		for(int i = 0 ; i< nodes.size(); i++){
			((DefaultTreeModel)this.getModel()).removeNodeFromParent(nodes.get(i));
		}
		
	}
	private ArrayList<DefaultMutableTreeNode> getSelectedNodes()
	{
		TreePath[] paths = this.getSelectionPaths();
		ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
		for(TreePath path : paths){
			nodes.add((DefaultMutableTreeNode)path.getLastPathComponent());
		}
		
		return nodes;
		
	}
	public DefaultMutableTreeNode findNode(String search) {
		
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
