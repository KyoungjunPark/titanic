package titanic;

import java.awt.event.ActionListener;

import javax.swing.*;

public class PopupMenu extends JPopupMenu{

	public PopupMenu(ActionListener menuListener, String... names){

		for(String name : names){
			JMenuItem menuItem = new JMenuItem(name);
			this.add(menuItem);
			menuItem.addActionListener(menuListener);
		}
	}

	
}
