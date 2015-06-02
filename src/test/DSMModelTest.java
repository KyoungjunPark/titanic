package test;

import static org.junit.Assert.*;
import model.CreateException;
import model.DSMModel;

import org.junit.Test;

public class DSMModelTest {

	@Test
	public void DSMModelCreatetest() {
		try {
			String string = "2\r\n1 2\r\n3 4\r\nAA\r\nBB";
			DSMModel model = new DSMModel(string);
			assertTrue(string.equals(model.toString()));
		} catch (CreateException e) {
			fail("Not yet implemented");
		}
        try {
            String string = "3\r\n1 2 3\r\n3 4 6\r\n7 8 9\r\nAA\r\nBB\r\nCC";
            DSMModel model = new DSMModel(string);
            assertTrue(string.equals(model.toString()));
        } catch (CreateException e) {
            fail("Not yet implemented");
        }
        try {
            String string = "5\r\n1 2\n\n3 4\n\nAA\n\nBB";
            DSMModel model = new DSMModel(string);
            fail("Not yet implemented");
        } catch (CreateException e) {
        }
        try {
            String string = "2\r\n1 2 3\n\n3 \n\nAA\n\nBB";
            DSMModel model = new DSMModel(string);
//            fail("Not yet implemented");
        } catch (CreateException e) {
        }
	}
}
