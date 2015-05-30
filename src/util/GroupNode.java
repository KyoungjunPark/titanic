package util;

import java.util.ArrayList;

/**
 * Created by kimjisoo on 5/19/15.
 */
public class GroupNode extends Node{
    public ArrayList<Node> childNodeArray = new ArrayList<Node>();
    private boolean isExpanded = false;
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

    /**
     * 현재 GroupNode 를 GreenTreeNode 로 변환합니다.
     * @return GreenTreeNode 의 객체
     */
    public GreenTreeNode getTreeNode(){
        GreenTreeNode root = new GreenTreeNode(this.getName(), this);
        root.setIsExpanded(this.isExpanded);
        for(Node node : this.childNodeArray){
            root.add(node.getTreeNode());
        }
        return root;
    }
    /**
     * 현재 GroupNode 를 중심으로 현재 그룹의 자식만의 ItemNode 를 가져옵니다.
     * 자식의 ItemNode 까지 필요하다면 getAllItemList 를 사용하세요.
     * ItemNode 는 {@link ItemNode} 를 참고하세요.
     * @return Node 의 ArrayList
     */
    public ArrayList<Node> getItemList(){
        ArrayList<Node> temp = new ArrayList<Node>();
        for(Node node : this.childNodeArray){
            if(node instanceof ItemNode){
                temp.add(node);
            }
        }
        return temp;
    }
    /**
     * 현재 GroupNode 를 중심으로 자식의 ItemNode 까지 포함한 모든 ItemNode 를 가져옵니다.
     * ItemNode 는 {@link ItemNode} 를 참고하세요.
     * @return Node 의 ArrayList
     */
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
    /**
     * 현재 GroupNode 를 중심으로 현재 그룹의 자식만의 GroupNode 를 가져옵니다.
     * 자식의 GroupNode 까지 필요하다면 getAllGroupList 를 사용하세요.
     * GroupNode 는 {@link GroupNode} 를 참고하세요.
     * @return Node 의 ArrayList
     */
    public ArrayList<Node> getGroupList(){
        ArrayList<Node> temp = new ArrayList<Node>();
        for(Node node : this.childNodeArray){
            if(node instanceof GroupNode){
                temp.add(node);
            }
        }
        return temp;
    }

    /**
     * 현재 GroupNode 를 중심으로 자식의 GroupNode 까지 포함한 모든 GroupNode 를 가져옵니다.
     * GroupNode 는 {@link GroupNode} 를 참고하세요.
     * @return Node 의 ArrayList
     */
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

    /**
     * 첫번째 GroupNode 를 가져옵니다.
     * getGroupList().get(0) 과 같은 동작을 합니다.
     * @return 현재 GroupNode 의 child 의 첫번째 GroupNode
     */
    public GroupNode getFirstChildGroupNode(){
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
        result += "</group>\r\n";
        return result;
    }
    public String toString(){
        return "<group name=\""+this.getName()+"\">\r\n"; // </group> 가 없음
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }
}
