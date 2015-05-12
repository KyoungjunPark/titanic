package titanic;

import java.awt.BorderLayout;
import java.awt.print.Book;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RightScreen {
	JPanel rightScreen;
	
	public RightScreen(){
		rightScreen = new JPanel(new BorderLayout(0,0));
		
		//test
		JTextField text = new JTextField("right side");
		rightScreen.add(text,BorderLayout.CENTER);
		
	}
	protected JPanel getRightScreen()
	{
			return rightScreen;
	}
}
