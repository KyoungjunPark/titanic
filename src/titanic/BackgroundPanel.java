package titanic;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.sun.glass.ui.Menu;

public class BackgroundPanel extends JFrame {
	
	private MainToolbar toolbar;
	private MenuBar menubar;
	private MainPanel mainPanel;
	
	public BackgroundPanel()
	{
		setTitle("Titanic");
		setSize(1400,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		//add Toolbar
		toolbar = new MainToolbar();
		getContentPane().add(toolbar, BorderLayout.NORTH);
			
		//add Menus
		menubar = new MenuBar();
		setJMenuBar(menubar);
		
		//add MainPanel
		mainPanel = new MainPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	
	}
	public void setAction()
	{
		MenuBarController menubarController = new MenuBarController(menubar);

	
	}
	
	
	/*
	protected void makeMenus()
	{

        
       
        about.addActionListener(new ActionListener()  								//ºÒ¿ÏÀü
        {  
         public void actionPerformed(ActionEvent e)  
         {  
        	 JPanel p1 = new JPanel();
    		 p1.setLayout(new GridLayout(4, 1));
    		 p1.add(new JLabel("Titanic"));
    		 p1.add(new JLabel("version 1.0.0"));
    		 p1.add(new JLabel("Chung-Ang University"));
    		 p1.add(new JLabel("Ji-Soo Kim, Kyung-Jun Park, Se-Hyeon Yang, Won-Se Lee, Ye-Lim Han"));
         JOptionPane.showMessageDialog(pane, p1);
         }  
        });   
        
       
	}

	*/
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BackgroundPanel().setVisible(true);
				}
			});
	}

}
