package titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class MenuBarController{
	
	public MenuBarController(MenuBar menubar) {
		/*
		menubar.getOpenDSMItem().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "button check");
				
			}
		});
*/
	}
	public static ActionListener OpenDSMItemListener(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "button check");
				
			}
		};
		
	}
}
