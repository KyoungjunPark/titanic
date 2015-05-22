package util;

import javax.swing.tree.DefaultMutableTreeNode;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by kimjisoo on 5/19/15.
 */
public class GroupNode extends Node{
    private ArrayList<Node> childNodeArray = new ArrayList<Node>();
    public GroupNode(){

    }
    public GroupNode(Node node, String name){
        this.setParentNode(node);
        this.setName(name);
    }
    public GroupNode(Node node){
        this(node, "");
    }
    public GroupNode(String name){
        this(null, name);
    }
    public void addItem(Node node){
        this.childNodeArray.add(node);
    }
    public DefaultMutableTreeNode getTreeNode(){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.getName());
        for(Node node : this.childNodeArray){
            root.add(node.getTreeNode());
        }
        return root;
    }
    public ArrayList<ItemNode> getItemList(){
        ArrayList<ItemNode> temp = new ArrayList<ItemNode>();
        for(Node node : this.childNodeArray){
            if(node instanceof GroupNode){

            }else if(node instanceof ItemNode){

            }
        }
        return null;
    }
}
