package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import titanic.MenuBar;

import com.sun.xml.internal.ws.api.server.Container;

import model.CreateException;
import model.ModelManager;

public class MenuBarController extends BackgroundPanelController{
	MenuBar menu;
	public MenuBarController(MenuBar menu)
	{
		this.menu = menu;
		
		init();
	}
	
	private void init()
	{
		//File->Open DSM action
		menu.setAction("Open DSM...", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File openFile;
				
				String dir = System.getProperty("user.dir");//this project's absolute path name
				JFileChooser fc = new JFileChooser(dir);
				fc.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return "DSM Files";
					}
					
					@Override
					public boolean accept(File f) {
						// TODO Auto-generated method stub
						return f.getName().endsWith(".dsm") || f.isDirectory();
					}
				});
				int yn = fc.showOpenDialog(null);
				if(yn != JFileChooser.APPROVE_OPTION) return;
				
				openFile = fc.getSelectedFile();
				try {
					ModelManager.sharedModelManager().createTitanicModel(openFile);
				} catch (CreateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//when file open success, then available icon must be changed!
				//Then Manager must give a message like "It's ok".
				
				//setting chaned when dsm file is open
				settingOpenDSM();
				
			}
		});
		menu.setAction("About...", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				java.awt.Container pane = null;
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

	
}
