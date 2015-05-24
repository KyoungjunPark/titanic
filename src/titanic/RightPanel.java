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
	
	// 필요 없을듯
	public int getID(){
		return this.id;
	}
	
	protected void setTableData(ArrayList<ArrayList<String>> newData){
		tableData=newData;
	}
	protected void makeTable(){
		table = new WholeDSMTable(tableData);
		this.add(table, BorderLayout.CENTER);
	}
	
	
}
