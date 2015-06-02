package model;

import java.io.File;

import util.GreenTreeNode;
import util.GroupNode;
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

    /**
     * TreeNode 로 부터 GroupNode 를 얻어 clsx 를 sync 합니다.
     * @param node {@link GreenTreeNode} 의 최상의 Node 를 받습니다.
     * @throws CreateException
     */
    protected void setTreeNode(GreenTreeNode node)throws CreateException{
        this.node = (GroupNode)node.getGroupNode();
        this.syncMetaModel();
    }
    public GroupNode getGroupNode(){
        if( this.getMetaModel() != null ) return this.getMetaModel().node;
        return this.node;
    };
    
    public void addNode(String newNodeName) {
    	
    }

    /**
     * 해당 CLSX 를 XML 형태로 만들어줍니다.
     * 최상의 노드는 cluster 이 됩니다.
     * @return XML 양식의 String 데이터
     */
    public String toString(){
        return "<cluster xmlns=\"http://rise.cs.drexel.edu/minos/clsx\">\r\n"+this.node.print()+"</cluster>\r\n";
    }
}
