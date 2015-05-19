package util;

import java.util.ArrayList;

/**
 * Created by kimjisoo on 5/19/15.
 */
public class GroupNode extends Node{
    private ArrayList<Node> childNodeArray = new ArrayList<Node>();
    public GroupNode(){

    }
    public GroupNode(String name){
        this.setName(name);
    }
    public void addItem(Node node){
        this.childNodeArray.add(node);
    }
    public void print(){
        System.out.println(this);
        for(Node node : this.childNodeArray){
            node.print();
        }
    }
    public String toString(){
        return "group"+this.getName();
    }
}
