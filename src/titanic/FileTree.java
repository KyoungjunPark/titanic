package titanic;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import util.TreeNode;
import model.EventManager;
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

		ArrayList<TreeNode> nodes = getSelectedNodes();
		TreePath[] treePath = new TreePath[nodes.size()];
		
		
		
		nodes = sortFromIndex(nodes);
		
		for (int i = 0; i < nodes.size(); i++) {
			TreeNode node = nodes.get(i);

			DefaultTreeModel model = (DefaultTreeModel) this.getModel();
			TreeNode root = (TreeNode) model
					.getRoot();
			TreeNode newNode;

			newNode = deepClone(node);
			model.insertNodeInto(newNode, (TreeNode)node.getParent(),
					node.getParent().getIndex(node) - 1);

			model.removeNodeFromParent(node);
			treePath[i] = new TreePath(newNode.getPath());
		}
		
		this.setSelectionPaths(treePath);

		syncWithModel();
	}

	protected void moveDown() {

		ArrayList<TreeNode> nodes = getSelectedNodes();
		TreePath[] treePath = new TreePath[nodes.size()];
		
		nodes = reverseSortFromIndex(nodes);
		
		for (int i = 0; i < nodes.size(); i++) {
			TreeNode node = nodes.get(i);
			DefaultTreeModel model = (DefaultTreeModel) this.getModel();
			TreeNode root = (TreeNode) model
					.getRoot();
			TreeNode newNode;

			newNode =  deepClone(node);
			model.insertNodeInto(newNode, (TreeNode)node.getParent(),
					node.getParent().getIndex(node) + 2);

			model.removeNodeFromParent(node);

			treePath[i] = new TreePath(newNode.getPath());
		}
		
		this.setSelectionPaths(treePath);
		
		syncWithModel();
	}

	private TreeNode deepClone(TreeNode source) {
		TreeNode newNode = (TreeNode) source
				.clone();
		Enumeration enumeration = source.children();
		while (enumeration.hasMoreElements()) {
			newNode.add(deepClone((TreeNode) enumeration
					.nextElement()));
		}
		return newNode;
	} 
	private ArrayList<TreeNode> sortFromIndex(ArrayList<TreeNode> nodes){
		
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
				TreeNode tmpNode = nodes.get(i);
				nodes.set(i, nodes.get(index));
				nodes.set(index, tmpNode);
			}
		}
		return nodes;

	} 
	private ArrayList<TreeNode> reverseSortFromIndex(ArrayList<TreeNode> nodes){
		
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
				TreeNode tmpNode = nodes.get(i);
				nodes.set(i, nodes.get(index));
				nodes.set(index, tmpNode);
			}
		}
		return nodes;
	} 

	protected void addItem() {
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		TreeNode root = (TreeNode) model.getRoot();
		root.add(new TreeNode("another_child"));
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

		ArrayList<TreeNode> nodes = getSelectedNodes();
		for (int i = 0; i < nodes.size(); i++) {
			((DefaultTreeModel) this.getModel()).removeNodeFromParent(nodes
					.get(i));
		}
		
		syncWithModel();
	}
	
	protected void groupTree(String groupName) {
		ArrayList<TreeNode> nodes = getSelectedNodes();
		TreeNode newGroup = new TreeNode(groupName);
		
		nodes = sortFromIndex(nodes);
		int index = nodes.get(0).getParent().getIndex(nodes.get(0));
		MutableTreeNode parent = (MutableTreeNode) nodes.get(0).getParent();

		for(int i=0; i<nodes.size(); i++) {
			TreeNode newNode = new TreeNode();
			newNode = (TreeNode) deepClone(nodes.get(i));
			newGroup.add(newNode);
		}
		
		((DefaultTreeModel) this.getModel()).insertNodeInto(newGroup, parent, index);
		delete();
		ModelManager.sharedModelManager().getCurrentTitanicModel().syncTreeNode(this.root);
		
	}
	
	protected void unGroupTree() {
		TreeNode node = getSelectedNodes().get(0);
		int index = node.getParent().getIndex(node);
		int childCount = node.getChildCount();
		
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		for (int i=0; i<childCount; i++) {
			//nodes.add((TreeNode) node.getChildAt(i));
			((DefaultTreeModel) this.getModel()).insertNodeInto
			((MutableTreeNode)node.getChildAt(0), (MutableTreeNode)node.getParent(), index+i);
		}
		delete();
		EventManager.callEvent("ungroupButtonDisable");
		ModelManager.sharedModelManager().getCurrentTitanicModel().syncTreeNode(this.root);
	}

	private ArrayList<TreeNode> getSelectedNodes() {
		TreePath[] paths = this.getSelectionPaths();
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		for (TreePath path : paths) {
			nodes.add((TreeNode) path.getLastPathComponent());
		}

		return nodes;

	}

	public TreeNode findNode(String search) {

		Enumeration nodeEnumeration = root.breadthFirstEnumeration();
		while (nodeEnumeration.hasMoreElements()) {
			TreeNode node = (TreeNode) nodeEnumeration
					.nextElement();
			String found = (String) node.getUserObject();
			if (search.equals(found)) {
				return node;
			}
		}
		return null;
	}

	public void rename(TreeNode node, String name){
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
