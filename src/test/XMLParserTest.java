package test;

import org.junit.Test;
import org.xml.sax.SAXException;
import util.GroupNode;
import util.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * Created by kimjisoo on 6/2/15.
 */
public class XMLParserTest {
    @Test
    public void XMLParserCreatetest(){
        XMLParser parser = new XMLParser();
        try{
            System.out.println("Test");
            GroupNode groupNode = parser.parseXML(new File("test/moka_ArchDRH.clsx"));
            System.out.println(groupNode.print());
        }catch (IOException e){
            fail(e+" ");
        }catch (SAXException e){
            fail(e+" ");
        }catch (ParserConfigurationException e){
            fail(e+" ");
        }
    }
}
