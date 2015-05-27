package util;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by kimjisoo on 5/23/15.
 */
public class TreeNode extends DefaultMutableTreeNode {
    private Node node;
    private boolean isExpanded = true;
    public TreeNode(){super();}
    public TreeNode(String string){
        super(string);
    }
    public TreeNode(String string, Node node){
        this(string);
        this.node = node;
    }
    public String getType(){
        return this.node.getType();
    }
    public Node getNode(){
        return this.node;
    }
    public Node getGroupNode() {
        GroupNode groupNode = new GroupNode(this.toString());
        for (int i = 0; i < this.getChildCount(); i++) {
            TreeNode node = (TreeNode) this.getChildAt(i);
            if (node.getChildCount() == 0)
                groupNode.addItem(new ItemNode(node.toString()));
            else
                groupNode.addItem(node.getGroupNode());
        }
        return groupNode;
    }

    public boolean isExpanded() {
        return isExpanded;
    }
    public void setIsExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }
}
