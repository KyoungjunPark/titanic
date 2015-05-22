package titanic;

import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import model.ModelManager;

public class FileTree extends JTree implements Controllerable{
	
	
	
	public FileTree(){
		setModel(null);
		/* test version
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("$root");
		
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("test1");
		node.add(new DefaultMutableTreeNode("test1-1"));
		node.add(new DefaultMutableTreeNode("test1-2"));
		root.add(node);
		
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("test2");
		node2.add(new DefaultMutableTreeNode("test2-1"));
		node2.add(new DefaultMutableTreeNode("test2-2"));
		root.add(node2);
		
		
		setModel(new DefaultTreeModel(root));
		new JScrollPane(this);
		*/
		
	}
	public void makeTree()
	{
		ModelManager.sharedModelManager().setTitanicModelID(0);
		DefaultMutableTreeNode root = ModelManager.sharedModelManager().getCurrentTitanicModel().getGroupNode().getTreeNode();
		setModel(new DefaultTreeModel(root));
		
		
	}
	@Override
	public void setAction(String title, ActionListener action) {
		
	}
}
