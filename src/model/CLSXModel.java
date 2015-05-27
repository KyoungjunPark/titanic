package model;

import java.io.File;

import util.GroupNode;
import util.GreenTreeNode;
import util.XMLParser;

public class CLSXModel extends Model{
    public CLSXModel()throws CreateException{}
    public CLSXModel(File file) throws CreateException{
        try{
            node = new XMLParser().parseXML(file);
            this.setFilePath(file.getAbsolutePath());
            this.setFileName(file.getName());
        }catch (Exception e){
            throw new CreateException("CLSX 만드는데 실패했음. 세세한 이유는 아직 안알랴줌"+e);
        }
    }
    public CLSXModel(GreenTreeNode node) throws CreateException{
        this.setTreeNode(node);
    }
    protected void setTreeNode(GreenTreeNode node)throws CreateException{
        this.node = (GroupNode)node.getGroupNode();
    }
    public GroupNode getGroupNode(){return this.node;};
    public String toString(){
        return "<cluster xmlns=\"http://rise.cs.drexel.edu/minos/clsx\">\r\n"+this.node.print()+"</cluster>\r\n";
    }
}
