package util;

import java.io.File;

import javax.swing.JOptionPane;

/**
 * Created by kimjisoo on 5/20/15.
 */
public class CommonUtils {
    /**
     * file object 의 filename 을 기준으로 extension 을 리턴합니다.
     * path/to/foo/bar.txt
     * return txt
     *
     * @param file file 오브젝트를 받습니다.
     * @return file's extension
     */
    public String getFileExtension(File file){
        String name = file.getName();
        try{
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e){
            return "";
        }
    }
}
