package util;

import java.io.File;

/**
 * Created by kimjisoo on 5/20/15.
 */
public class CommonUtils {
    public String getFileExtension(File file){
        String name = file.getName();
        try{
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e){
            return "";
        }
    }
}
