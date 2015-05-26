package model;

import util.GroupNode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    public void save()throws SaveException{
        if(this.getFilePath() == null)
            throw new SaveException("file 의 경로를 지정해주세요.");
        BufferedWriter output = null;
        try {
            File file = new File(this.getFilePath());
            output = new BufferedWriter(new FileWriter(file));
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
    public GroupNode getGroupNode(){return null;}
    protected boolean isEdit(){
        return this.isEdit;
    }
    protected void setIsEdit(boolean isEdit){
        this.isEdit = isEdit;
    }
}
