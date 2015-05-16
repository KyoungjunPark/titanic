package Model;

import junit.framework.TestCase;

import org.junit.Test;

public class DSMModelTest extends TestCase{
	private DSMModel dsm;
	
	protected void setUp() throws Exception {
		dsm = new DSMModel();
		dsm.readFile(DSMModelTest.class.getResource("").getPath()+"../../model_data/smalltest/smalltest.dsm");
		
	};
	@Test
	public void testReadFile() {
		/*
		assertEquals(4, dsm.getDependencyNumber());
		
		int[][] array =  dsm.getDependencyRelation();
		
		assertEquals(0,array[0][0]);
		assertEquals(0,array[0][1]);
		assertEquals(1,array[0][2]);
		assertEquals(1,array[0][3]);
		assertEquals(1,array[1][0]);
		assertEquals(1,array[1][1]);
		assertEquals(0,array[1][2]);
		assertEquals(0,array[1][3]);
		assertEquals(0,array[2][0]);
		assertEquals(0,array[2][1]);
		assertEquals(1,array[2][2]);
		assertEquals(1,array[2][3]);
		assertEquals(1,array[3][0]);
		assertEquals(1,array[3][1]);
		assertEquals(1,array[3][2]);
		assertEquals(0,array[3][3]);
		
		assertEquals("edu.drexel.cs.rise.titan.action.cluster.SaveAction", dsm.getElementsName()[0]);
		assertEquals("edu.drexel.cs.rise.titan.action.ExportExcelAction", dsm.getElementsName()[1]);
		assertEquals("edu.drexel.cs.rise.titan.util.ClusterUtilities", dsm.getElementsName()[2]);
		assertEquals("edu.drexel.cs.rise.titan.util.ActionUtilities", dsm.getElementsName()[3]);
		*/
	}

}
