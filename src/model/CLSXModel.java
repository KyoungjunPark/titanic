package model;

import java.io.File;
import util.GroupNode;
import util.XMLParser;

class CLSXModel{
    GroupNode node;
    public CLSXModel(File file)throws CreateException{
        try{
            node = new XMLParser().parseXML(file);
        }catch (Exception e){
            throw new CreateException("CLSX 만드는데 실패했음. 세세한 이유는 아직 안알랴줌");
        }
    }
}
