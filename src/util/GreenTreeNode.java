package util;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by kimjisoo on 5/23/15.
 */
public class GreenTreeNode extends DefaultMutableTreeNode {
    private Node node;
    private boolean isExpanded = false;
    public GreenTreeNode(){super();}
    public GreenTreeNode(String string){
        super(string);
    }
    public GreenTreeNode(String string, Node node){
        this(string);
        this.node = node;
    }
    public String getType(){
        return this.node.getType();
    }
    public void setNode(Node node){
        this.node = node;
    }
    public Node getNode(){
        return this.node;
    }
    public Node getGroupNode() {
        GroupNode groupNode = new GroupNode(this.toString());
        groupNode.setExpanded(this.isExpanded);
        for (int i = 0; i < this.getChildCount(); i++) {
            GreenTreeNode node = (GreenTreeNode) this.getChildAt(i);
            if (node.getChildCount() == 0)
                groupNode.addItem(new ItemNode(node.toString()));
            else
                groupNode.addItem(node.getGroupNode());
        }
        return groupNode;
    }

    public void sortAlphabetic(){
        ArrayList<GreenTreeNode> nodes = new ArrayList<>();
        for(int i = 0 ; i < this.getChildCount() ; i++) {
            nodes.add((GreenTreeNode) this.getChildAt(i));
        }


        Comparator<GreenTreeNode> sort = new Comparator<GreenTreeNode>() {
            @Override
            public int compare(GreenTreeNode o1, GreenTreeNode o2) {
                return o1.toString().toLowerCase().compareTo(o2.toString().toLowerCase());
            }
        };


        Collections.sort(nodes, sort);
        this.removeAllChildren();

        for(GreenTreeNode node : nodes) {
            this.add(node);
        }


    }

    public boolean isExpanded() {
        return isExpanded;
    }
    public void setIsExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }
    public GreenTreeNode clone() {
        GreenTreeNode node = (GreenTreeNode)super.clone();
        node.setNode(this.getNode());
        node.setIsExpanded(this.isExpanded());
        return node;
    }
}
