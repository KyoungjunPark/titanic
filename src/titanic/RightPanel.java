package titanic;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import model.ModelManager;

public class RightPanel extends JPanel implements Controllerable{
	
	private int id;
	private WholeDSMTable table;
	private ArrayList<ArrayList<String>> tableData;
	
	
	public RightPanel(){
		setLayout(new BorderLayout(0,0));
	
	}

	@Override
	public void setAction(String title, ActionListener action) {
		
	}
	public void setID(int id)
	{
		this.id = id;
	}

	public int getID(){
		return this.id;
	}
	protected void makeTable(){
		tableData=ModelManager.sharedModelManager().getCurrentTitanicModel().getMatrixData();
		table = new WholeDSMTable(tableData);
		this.add(table, BorderLayout.CENTER);
		
	}
	
	
}
