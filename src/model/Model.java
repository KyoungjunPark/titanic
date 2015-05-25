package model;

import util.GroupNode;

/**
 * Created by kimjisoo on 5/20/15.
 */
abstract class Model {
    private String filePath;
    protected GroupNode node = null;
    public String getFilePath() {
        return filePath;
    }
    public String getFileName() {
        if(filePath.lastIndexOf("/") != -1)
            return filePath.substring(filePath.lastIndexOf("/"));
        else
            return filePath.substring(filePath.lastIndexOf("\\"));
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public GroupNode getGroupNode(){return null;}
}
