package test;

import static org.junit.Assert.*;
import model.CreateException;
import model.DSMModel;

import org.junit.Test;

public class DSMModelTest {

    /**
     * 모든 String 은 CRLF 를 따른다.
     * CR 을 따를경우 OS X 및 Nix계열에서는 안정적으로 작동을 하지만, Windows 에서 작성한 파일에 경우 제대로 동작을 하지 못하는 케이스가 발생하였다.
     * 따라서 주 타겟을 Windows 로 설정하여 CRLF 를 사용하였다.
     */
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
        DSMModel model = new DSMModel(5);
        String result = "5\r\n0 0 0 0 0\r\n0 0 0 0 0\r\n0 0 0 0 0\r\n0 0 0 0 0\r\n0 0 0 0 0\r\nentity_1\r\nentity_2\r\nentity_3\r\nentity_4\r\nentity_5";
        assertEquals(result, model.toString());
	}
}
