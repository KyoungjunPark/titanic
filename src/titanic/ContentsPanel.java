package titanic;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

public class ContentsPanel extends JTabbedPane implements Controllerable {
	
	private ArrayList<RightPanel> contents;
	
	public ContentsPanel(){
		contents = new ArrayList<RightPanel>();
		
		RightPanel firstPage = new RightPanel();
		contents.add(firstPage);
	}
	public void addRightPanel(RightPanel rightPanel)
	{
		contents.add(rightPanel);
	}
	public ArrayList<RightPanel> getRightPanel()
	{
		return contents;
	}
	@Override
	public void setAction(String title, ActionListener action) {
		
	}
	
}
