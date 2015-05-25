package model;

import java.io.File;
import java.security.acl.Group;

import util.GroupNode;
import util.TreeNode;
import util.XMLParser;

class CLSXModel extends Model{
    public CLSXModel(File file) throws CreateException{
        try{
            node = new XMLParser().parseXML(file);
        }catch (Exception e){
            throw new CreateException("CLSX 만드는데 실패했음. 세세한 이유는 아직 안알랴줌"+e);
        }
    }
    public CLSXModel(TreeNode node) throws CreateException{
        this.setTreeNode(node);
    }
    protected void setTreeNode(TreeNode node)throws CreateException{
        System.out.println(node+" ! ");
        GroupNode g = (GroupNode)node.getGroupNode();
        g.print();
        this.setIsEdit(true);
    }
    public GroupNode getGroupNode(){return this.node;};
}
