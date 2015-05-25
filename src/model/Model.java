package model;

import util.GroupNode;

/**
 * Created by kimjisoo on 5/20/15.
 */
abstract class Model {
    private String filePath;
    private String fileName;
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
}
