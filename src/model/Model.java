package model;

import util.GroupNode;

/**
 * Created by kimjisoo on 5/20/15.
 */
abstract class Model {
    private String filePath;
    private String fileName;
    private boolean isEdit = false;
    protected GroupNode node = null;
    public String getFilePath() {
        return filePath;
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
    public GroupNode getGroupNode(){return null;}
    protected boolean isEdit(){
        return this.isEdit;
    }
    protected void setIsEdit(boolean isEdit){
        this.isEdit = isEdit;
    }
}
