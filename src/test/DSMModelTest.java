package test;

import static org.junit.Assert.*;
import model.CreateException;
import model.DSMModel;
import util.XMLParser;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

public class DSMModelTest {

	@Test
	public void DSMModeltest() {
		try {
			String string = "2\n1 2\n3 4\nAA\nBB";
			DSMModel model = new DSMModel(string);
			assertTrue(string.equals(model.toString()));
		} catch (CreateException e) {
			fail("Not yet implemented");
		}
        try {
            String string = "3\n1 2 3\n3 4 6\n7 8 9\nAA\nBB\nCC";
            DSMModel model = new DSMModel(string);
            assertTrue(string.equals(model.toString()));
        } catch (CreateException e) {
            fail("Not yet implemented");
        }
        try {
            String string = "5\n1 2\n3 4\nAA\nBB";
            DSMModel model = new DSMModel(string);
            fail("Not yet implemented");
        } catch (CreateException e) {
        }
        try {
            String string = "2\n1 2 3\n3 \nAA\nBB";
            DSMModel model = new DSMModel(string);
            fail("Not yet implemented");
        } catch (CreateException e) {
        }
	}
    @Test
    public void XMLParsertest(){
        XMLParser parser = new XMLParser();
        try{
            parser.parseXML(new File("moka_ArchDRH.clsx"));
        }catch (IOException e){
            
        }catch (SAXException e){

        }catch (ParserConfigurationException e){

        }
    }

}
