package model;

/**
 * Created by kimjisoo on 5/20/15.
 */
abstract class Model {
    private String filePath;

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
