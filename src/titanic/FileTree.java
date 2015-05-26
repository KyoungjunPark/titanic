package titanic;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import util.TreeNode;
import model.ModelManager;

public class FileTree extends JTree implements Controllerable {
	private TreeNode root;

	public FileTree() {
		this.setModel(null);
	}

	public void makeTree() {

		root = ModelManager.sharedModelManager().getCurrentTitanicModel()
				.getGroupNode().getTreeNode();

		this.setModel(new DefaultTreeModel(root));
		this.collapseRow(0);

		this.setSelectionPath(new TreePath(root));

	}

	protected void moveUp() {

		ArrayList<DefaultMutableTreeNode> nodes = getSelectedNodes();
		TreePath[] treePath = new TreePath[nodes.size()];
		
		
		
		nodes = sortFromIndex(nodes);
		
		for (int i = 0; i < nodes.size(); i++) {
			DefaultMutableTreeNode node = nodes.get(i);

			DefaultTreeModel model = (DefaultTreeModel) this.getModel();
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) model
					.getRoot();
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();

			newNode = (DefaultMutableTreeNode) deepClone(node);
			model.insertNodeInto(newNode, (DefaultMutableTreeNode)node.getParent(),
					node.getParent().getIndex(node) - 1);

			node.removeFromParent();

			model.reload(root);
			treePath[i] = new TreePath(newNode.getPath());
		}
		
		this.setSelectionPaths(treePath);
		
		syncWithModel();
	}

	protected void moveDown() {

		ArrayList<DefaultMutableTreeNode> nodes = getSelectedNodes();
		TreePath[] treePath = new TreePath[nodes.size()];
		
		nodes = reverseSortFromIndex(nodes);
		
		for (int i = 0; i < nodes.size(); i++) {
			DefaultMutableTreeNode node = nodes.get(i);
			DefaultTreeModel model = (DefaultTreeModel) this.getModel();
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) model
					.getRoot();
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();

			newNode = (DefaultMutableTreeNode) deepClone(node);
			model.insertNodeInto(newNode, (DefaultMutableTreeNode)node.getParent(),
					node.getParent().getIndex(node) + 2);

			node.removeFromParent();

			model.reload(root);
			treePath[i] = new TreePath(newNode.getPath());
		}
		
		this.setSelectionPaths(treePath);
		
		syncWithModel();
	}

	private DefaultMutableTreeNode deepClone(DefaultMutableTreeNode source) {
		DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) source
				.clone();
		Enumeration enumeration = source.children();
		while (enumeration.hasMoreElements()) {
			newNode.add(deepClone((DefaultMutableTreeNode) enumeration
					.nextElement()));
		}
		return newNode;
	} 
	private ArrayList<DefaultMutableTreeNode> sortFromIndex(ArrayList<DefaultMutableTreeNode> nodes){
		
		for(int i = 0 ; i<nodes.size() ; i++){
			int min = root.getIndex(nodes.get(i));
			int index = i;
			for(int j = i+1 ;  j <nodes.size(); j++){
				if(root.getIndex(nodes.get(j)) < min){
					min = root.getIndex(nodes.get(j));
					index = j;
				}
			}
			if(i != index){
				//exchange i & index 's contents
				DefaultMutableTreeNode tmpNode = nodes.get(i);
				nodes.set(i, nodes.get(index));
				nodes.set(index, tmpNode);
			}
		}
		return nodes;

	} 
	private ArrayList<DefaultMutableTreeNode> reverseSortFromIndex(ArrayList<DefaultMutableTreeNode> nodes){
		
		for(int i = 0 ; i<nodes.size() ; i++){
			int max = root.getIndex(nodes.get(i));
			int index = i;
			for(int j = i+1 ;  j <nodes.size(); j++){
				if(root.getIndex(nodes.get(j)) > max){
					max = root.getIndex(nodes.get(j));
					index = j;
				}
			}
			if(i != index){
				//exchange i & index 's contents
				DefaultMutableTreeNode tmpNode = nodes.get(i);
				nodes.set(i, nodes.get(index));
				nodes.set(index, tmpNode);
			}
		}
		return nodes;
	} 

	protected void addItem() {
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		root.add(new DefaultMutableTreeNode("another_child"));
		model.reload(root);

	}

	protected void expandAll(int startingIndex, int rowCount) {

		for (int i = startingIndex; i < rowCount; i++) {
			this.expandRow(i);
		}

		if (this.getRowCount() != rowCount) {
			expandAll(rowCount, this.getRowCount());
		}
	}

	protected void collapseAll() {
		for (int i = this.getRowCount() - 1; i >= 0; i--) {
			this.collapseRow(i);
		}
	}

	protected void delete() {

		ArrayList<DefaultMutableTreeNode> nodes = getSelectedNodes();
		for (int i = 0; i < nodes.size(); i++) {
			((DefaultTreeModel) this.getModel()).removeNodeFromParent(nodes
					.get(i));
		}
		
		syncWithModel();
	}
	
	protected void groupTree(String groupName) {
		ArrayList<DefaultMutableTreeNode> nodes = getSelectedNodes();
		TreeNode newGroup = new TreeNode(groupName);
		
		nodes = sortFromIndex(nodes);
		int index = nodes.get(0).getParent().getIndex(nodes.get(0));
		MutableTreeNode parent = (MutableTreeNode) nodes.get(0).getParent();

		for(int i=0; i<nodes.size(); i++) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();
			newNode = (DefaultMutableTreeNode) deepClone(nodes.get(i));
			newGroup.add(newNode);
		}
		
		((DefaultTreeModel) this.getModel()).insertNodeInto(newGroup, parent, index);
		delete();
		ModelManager.sharedModelManager().getCurrentTitanicModel().syncTreeNode(this.root);
		
	}
	
	protected void unGroupTree() {
		
	}

	private ArrayList<DefaultMutableTreeNode> getSelectedNodes() {
		TreePath[] paths = this.getSelectionPaths();
		ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
		for (TreePath path : paths) {
			nodes.add((DefaultMutableTreeNode) path.getLastPathComponent());
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

	public void rename(DefaultMutableTreeNode node, String name){
		node.setUserObject(name);
		
		repaint();
		syncWithModel();
	}
	private void syncWithModel()
	{
		ModelManager.sharedModelManager().getCurrentTitanicModel().syncTreeNode(this.root);
	}
	@Override
	public void setAction(String title, ActionListener action) {

	}
}
