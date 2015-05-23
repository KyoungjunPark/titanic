package titanic;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class RightPanel extends JPanel implements Controllerable{
	
	private int id;
	
	
	public RightPanel(){
		setLayout(new BorderLayout(0,0));
	}

	@Override
	public void setAction(String title, ActionListener action) {
		// TODO Auto-generated method stub
		
	}
	public void setID(int id)
	{
		this.id = id;
	}
	
}
