package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by A on 15. 5. 22..
 */
public class JSFiles {
    /**
     * file 에 있는 모든 내용을 스트링으로 바꾸어 줍니다.
     * @param file 이미 오픈되어 있는 파일 객체를 받습니다.
     * @return String
     * @throws IOException 파일을 읽는 과정에서 IOException 이발생할 수 있습니다.
     */
    static public String getAllString(File file)throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        char[] buffer = new char[1024];

        while(reader.read(buffer) >0){
            content.append(buffer);
        }
        return content.toString();
    }
    /**
     * file object 의 filename 을 기준으로 extension 을 리턴합니다.
     * path/to/foo/bar.txt
     * return txt
     *
     * @param file 이미 오픈되어 있는 파일 객체를 받습니다.
     * @return file's extension
     */
    static public String getFileExtension(File file){
        String name = file.getName();
        try{
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e){
            return "";
        }
    }
}
