package model;

import util.GroupNode;
import util.Node;

import java.io.*;

/**
 * Created by kimjisoo on 5/20/15.
 */
abstract class Model {
    private String filePath;
    private String fileName;
    private boolean isEdit = false;
    private MetaModel metaModel = null;
    private MetaModel storeMetaModel = null;
    protected GroupNode node = null;
    public String getFilePath() {
        return filePath;
    }
    /*
        must modify dsm save's feature because move up & down & delete
     */
    public void save()throws SaveException{
        if(this.getFilePath() == null)
            throw new SaveException("file 의 경로를 지정해주세요.");
        BufferedWriter output = null;
        try {
            File file = new File(this.getFilePath());
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()),"UTF8"));
            output.write(this.toString());
            output.close();
        } catch ( IOException e ) {
            throw new CreateException("파일 에러");
        }
        this.setIsEdit(false);
    }
    public void save(String path)throws SaveException{
        BufferedWriter output = null;
        try {
            File file = new File(path);
            output = new BufferedWriter(new FileWriter(file));
            output.write(this.toString());
            output.close();
        } catch ( IOException e ) {
            throw new CreateException("파일 에러");
        }
        this.setFilePath(path);
        this.setIsEdit(false);
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public void setGroupNode(GroupNode node){
        if(this.metaModel == null)
            this.node = node;
        else{
            this.metaModel.node = node;
            this.syncMetaModel();
        }
    }
    public GroupNode getGroupNode(){return null;}
    public void addNode(String newNodeName){}
    protected boolean isEdit(){
        return this.isEdit;
    }
    public void setIsEdit(boolean isEdit){
        this.isEdit = isEdit;
    }

    protected MetaModel getMetaModel() {
        return metaModel;
    }

    protected void setMetaModel(String nodeName) {
        for(Node groupNode : this.node.getAllGroupList()){
            if(groupNode.getName().compareTo(nodeName) == 0){
                this.metaModel = new MetaModel((GroupNode)groupNode);
                break;
            }
        }
    }
    protected void syncMetaModel(){
        if( metaModel == null) return;
        for(Node groupNode : this.node.getAllGroupList()){
            if(groupNode.getName().compareTo(metaModel.node.getName()) == 0){
                GroupNode temp = (GroupNode)groupNode;
                temp.setExpanded(metaModel.node.isExpanded());
                temp.childNodeArray = metaModel.node.childNodeArray;
                break;
            }
        }
    }
    protected void saveMetaModel(){
        this.storeMetaModel = this.metaModel;
        this.metaModel = null;
    }
    protected void loadMetaModel(){
        if(this.metaModel == null)
            this.metaModel = this.storeMetaModel;
    }
}
