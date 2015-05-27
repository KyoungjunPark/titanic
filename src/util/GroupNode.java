package util;

import model.T3;

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
    public GreenTreeNode getTreeNode(){
        GreenTreeNode root = new GreenTreeNode(this.getName(), this);
        for(Node node : this.childNodeArray){
            root.add(node.getTreeNode());
        }
        return root;
    }
    public ArrayList<Node> getItemList(){
        ArrayList<Node> temp = new ArrayList<Node>();
        for(Node node : this.childNodeArray){
            if(node instanceof ItemNode){
                temp.add(node);
            }
        }
        return temp;
    }
    public ArrayList<Node> getAllItemList(){
        ArrayList<Node> temp = new ArrayList<Node>();
        for(Node node : this.childNodeArray){
            if(node instanceof GroupNode){
                temp.addAll(((GroupNode) node).getAllItemList());
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
            }
        }
        return temp;
    }
    public ArrayList<Node> getAllGroupList(){
        ArrayList<Node> temp = new ArrayList<Node>();
        for(Node node : this.childNodeArray){
            if(node instanceof GroupNode){
                temp.add(node);
                temp.addAll(((GroupNode) node).getAllGroupList());
            }
        }
        return temp;
    }
    public GroupNode getFirstChildGroupNode(){
        ArrayList<Node> temp = new ArrayList<Node>();
        for(Node node : this.childNodeArray){
            if(node instanceof GroupNode){
                return (GroupNode)node;
            }
        }
        return null;
    }

    /**
     * Node 의 Group 정보를 T3 형태로 표시합니다.
     * T3 은 다음을 참조하세요. {@link T3}
     * 현재 Group 의 depth 는 0이라 가정하며 생략합니다.
     * 이하의 그룹에 대해서는 (depth, 시작 index, 끝 index) 의 T3 정보를 갖는 ArrayList 를 반환합니다.
     * @return T3 데이터를 갖는 ArrayList 입니다.
     */
    public ArrayList<T3> getGroupData(){
        ArrayList<Node> itemList = this.getAllItemList();
        ArrayList<Node> currentGroupList;
        ArrayList<Node> nextGroupList = this.getGroupList();
        ArrayList<T3> groupData = new ArrayList<T3>();
        int depth = 1;
        while(nextGroupList.size() != 0){
            currentGroupList = nextGroupList;
            nextGroupList = new ArrayList<Node>();
            for( Node node : currentGroupList){
                GroupNode temp = (GroupNode)node;
                T3 tuple = new T3(depth, itemList.indexOf(temp.getItemList().get(0)), itemList.indexOf(temp.getItemList().get(temp.getItemList().size()-1)));
                if( tuple.getFirst() >= 0 && tuple.getLast() >= 0)
                    groupData.add(tuple);
                nextGroupList.addAll(temp.getGroupList());
            }
            depth++;
        }
        return groupData;
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
        result += "</group>\r\n";
        return result;
    }
    public String toString(){
        return "<group name=\""+this.getName()+"\">\r\n"; // </group> 가 없음
    }
}
