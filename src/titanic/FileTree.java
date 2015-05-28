package titanic;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import util.GreenTreeNode;
import model.EventManager;
import model.ModelManager;

public class FileTree extends JTree implements Controllerable {
	private GreenTreeNode root;

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

		ArrayList<GreenTreeNode> nodes = getSelectedNodes();
		TreePath[] treePath = new TreePath[nodes.size()];
		
		
		
		nodes = sortFromIndex(nodes);
		
		for (int i = 0; i < nodes.size(); i++) {
			GreenTreeNode node = nodes.get(i);

			DefaultTreeModel model = (DefaultTreeModel) this.getModel();
			GreenTreeNode root = (GreenTreeNode) model
					.getRoot();
			GreenTreeNode newNode;

			newNode = deepClone(node);
			model.insertNodeInto(newNode, (GreenTreeNode) node.getParent(),
					node.getParent().getIndex(node) - 1);

			model.removeNodeFromParent(node);
			treePath[i] = new TreePath(newNode.getPath());
		}
		
		this.setSelectionPaths(treePath);

		syncWithModel();
	}

	protected void moveDown() {

		ArrayList<GreenTreeNode> nodes = getSelectedNodes();
		TreePath[] treePath = new TreePath[nodes.size()];
		
		nodes = reverseSortFromIndex(nodes);
		
		for (int i = 0; i < nodes.size(); i++) {
			GreenTreeNode node = nodes.get(i);
			DefaultTreeModel model = (DefaultTreeModel) this.getModel();
			GreenTreeNode root = (GreenTreeNode) model
					.getRoot();
			GreenTreeNode newNode;

			newNode =  deepClone(node);
			model.insertNodeInto(newNode, (GreenTreeNode) node.getParent(),
					node.getParent().getIndex(node) + 2);

			model.removeNodeFromParent(node);

			treePath[i] = new TreePath(newNode.getPath());
		}
		
		this.setSelectionPaths(treePath);
		
		syncWithModel();
	}

	private GreenTreeNode deepClone(GreenTreeNode source) {
		GreenTreeNode newNode = (GreenTreeNode) source
				.clone();
		Enumeration enumeration = source.children();
		while (enumeration.hasMoreElements()) {
			newNode.add(deepClone((GreenTreeNode) enumeration
					.nextElement()));
		}
		return newNode;
	} 
	private ArrayList<GreenTreeNode> sortFromIndex(ArrayList<GreenTreeNode> nodes){
		
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
				GreenTreeNode tmpNode = nodes.get(i);
				nodes.set(i, nodes.get(index));
				nodes.set(index, tmpNode);
			}
		}
		return nodes;

	} 
	private ArrayList<GreenTreeNode> reverseSortFromIndex(ArrayList<GreenTreeNode> nodes){
		
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
				GreenTreeNode tmpNode = nodes.get(i);
				nodes.set(i, nodes.get(index));
				nodes.set(index, tmpNode);
			}
		}
		return nodes;
	} 

	protected void addItem() {
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		GreenTreeNode root = (GreenTreeNode) model.getRoot();
		root.add(new GreenTreeNode("another_child"));
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

		ArrayList<GreenTreeNode> nodes = getSelectedNodes();
		for (int i = 0; i < nodes.size(); i++) {
			((DefaultTreeModel) this.getModel()).removeNodeFromParent(nodes
					.get(i));
		}
		
		syncWithModel();
	}
	
	protected void groupTree(String groupName) {
		ArrayList<GreenTreeNode> nodes = getSelectedNodes();
		GreenTreeNode newGroup = new GreenTreeNode(groupName);
		
		nodes = sortFromIndex(nodes);
		int index = nodes.get(0).getParent().getIndex(nodes.get(0));
		MutableTreeNode parent = (MutableTreeNode) nodes.get(0).getParent();

		for(int i=0; i<nodes.size(); i++) {
			GreenTreeNode newNode = new GreenTreeNode();
			newNode = (GreenTreeNode) deepClone(nodes.get(i));
			newGroup.add(newNode);
		}
		
		((DefaultTreeModel) this.getModel()).insertNodeInto(newGroup, parent, index);
		delete();
		ModelManager.sharedModelManager().getCurrentTitanicModel().syncTreeNode(this.root);
		
	}
	
	protected void unGroupTree() {
		GreenTreeNode node = getSelectedNodes().get(0);
		int index = node.getParent().getIndex(node);
		int childCount = node.getChildCount();
		
		ArrayList<GreenTreeNode> nodes = new ArrayList<GreenTreeNode>();
		for (int i=0; i<childCount; i++) {
			//nodes.add((GreenTreeNode) node.getChildAt(i));
			((DefaultTreeModel) this.getModel()).insertNodeInto
			((MutableTreeNode)node.getChildAt(0), (MutableTreeNode)node.getParent(), index+i);
		}
		delete();
		EventManager.callEvent("ungroupButtonDisable");
		ModelManager.sharedModelManager().getCurrentTitanicModel().syncTreeNode(this.root);
	}

	private ArrayList<GreenTreeNode> getSelectedNodes() {
		TreePath[] paths = this.getSelectionPaths();
		ArrayList<GreenTreeNode> nodes = new ArrayList<GreenTreeNode>();
		for (TreePath path : paths) {
			nodes.add((GreenTreeNode) path.getLastPathComponent());
		}

		return nodes;

	}

	public GreenTreeNode findNode(String search) {

		Enumeration nodeEnumeration = root.breadthFirstEnumeration();
		while (nodeEnumeration.hasMoreElements()) {
			GreenTreeNode node = (GreenTreeNode) nodeEnumeration
					.nextElement();
			String found = (String) node.getUserObject();
			if (search.equals(found)) {
				return node;
			}
		}
		return null;
	}

	public void rename(GreenTreeNode node, String name){
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
