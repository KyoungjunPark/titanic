package titanic;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.T3;

public class RightPanel extends JPanel implements Controllerable{
	
	private int id;
	private DependencyTable table;
	private ArrayList<ArrayList<String>> tableData;
	private ArrayList<T3> groupInfo;
	
	private boolean setShowRowLabels;	
	/**
	 * DSM을 오픈했을 때 그려지는 테이블 하나를 포함하는 패널입니다.
	 * 테이블에 들어갈 데이터를 DependencyTable의 함수 인자로 넘겨주면 앞에서 넘겨진 데이터의 테이블이 생성됩니다.
	 * */
	public RightPanel(){
		setLayout(new BorderLayout(0,0));	

	}
	public RightPanel(int id){
		this.id = id;
	
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

	
	// redraw를 통해 테이블을 갱신할 때 필요한 함수.
	/* 전혀안씀ㅋㅋ
	protected void setTableData(ArrayList<ArrayList<String>> newData){
		tableData=newData;
	}
	protected void setGroups(ArrayList<T3> tupleList){
		
		this.tupleList=tupleList;
	}
	*/
	
	protected void redrawPanel(ArrayList<ArrayList<String>> newData, ArrayList<T3> groupInfo){
		tableData=newData;
		this.groupInfo=groupInfo;
		table = new DependencyTable(tableData, this.groupInfo, this.setShowRowLabels);
		this.removeAll();
		
		this.setLayout(new BorderLayout());
		this.add(table, BorderLayout.CENTER);
	}
	
	public void setShowRowLabels(boolean state) {
		this.setShowRowLabels=state;
	}
	
}
