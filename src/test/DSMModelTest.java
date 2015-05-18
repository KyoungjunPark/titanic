package test;

import static org.junit.Assert.*;
import model.CreateException;
import model.DSMModel;

import org.junit.Test;
public class DSMModelTest {

	@Test
	public void test() {
		try {
			DSMModel model = new DSMModel("2\n1 2\n3 4\nAA\nBB");
		} catch (CreateException e) {
			fail("Not yet implemented");
		}
	}

}
