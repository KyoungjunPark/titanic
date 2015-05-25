package util;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by KimJiSoo on 15. 5. 19..
 */
public abstract class Node {
    private String name;
    private Node parentNode;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setParentNode(Node parentNode){
        this.parentNode = parentNode;
    }
    public Node getParentNode(){
        return this.parentNode;
    }

    /**
     * 자신을 트리형태로 바꾸어 돌려줍니다.
     * GroupNode 의 경우에는 하위 컨텐츠까지 트리로 바꾸어줍니다.
     *
     * @return {@link DefaultMutableTreeNode} TreeNode 를 돌려줍니다.
     */
    abstract public DefaultMutableTreeNode getTreeNode();
    abstract public String getType();
    public String toString(){
        return getType()+" "+getName()+"\n";
    }
}
