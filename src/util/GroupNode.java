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
    public TreeNode getTreeNode(){
        TreeNode root = new TreeNode(this.getName(), this);
        for(Node node : this.childNodeArray){
            root.add(node.getTreeNode());
        }
        return root;
    }
    public ArrayList<Node> getItemList(){
        ArrayList<Node> temp = new ArrayList<Node>();
        for(Node node : this.childNodeArray){
            if(node instanceof GroupNode){
                temp.addAll(((GroupNode) node).getItemList());
            }else if(node instanceof ItemNode){
                temp.add(node);
            }
        }
        return temp;
    }
    public ArrayList<Node> getGroupList(){
        ArrayList<Node> temp = new ArrayList<Node>();
        for(Node node : this.childNodeArray){
            if(node instanceof GroupNode){
                temp.add(node);
                temp.addAll(((GroupNode) node).getGroupList());
            }
        }
        return temp;
    }
    public GroupNode getChildGroupNode(){
        ArrayList<Node> temp = new ArrayList<Node>();
        for(Node node : this.childNodeArray){
            if(node instanceof GroupNode){
                return (GroupNode)node;
            }
        }
        return null;
    }
    public String getType(){
        return "G";
    }
    public String print(){
        String result = this.toString();
        for(Node node: this.childNodeArray){
            if(node instanceof GroupNode){
                result += ((GroupNode) node).print();
            }else
                result += node.toString();
        }
        result += "</group>\n";
        return result;
    }
    public String toString(){
        return "<group name=\""+this.getName()+"\">\n"; // </group> 가 없음
    }
}
