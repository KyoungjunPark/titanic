package titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopupMenu extends JPopupMenu{

	public PopupMenu(ActionListener menuListener){
		
		JMenuItem rename =  new JMenuItem("Rename");
		JMenuItem sort = new JMenuItem("Sort");
		JMenuItem duplicate = new JMenuItem("Duplicate");
		JMenuItem edit = new JMenuItem("Edit");

		this.add(rename);
		this.add(sort);
		this.addSeparator();
		this.add(duplicate);
		this.add(edit);
		
		rename.addActionListener(menuListener);
		sort.addActionListener(menuListener);
		duplicate.addActionListener(menuListener);
		edit.addActionListener(menuListener);
	}
	
}
