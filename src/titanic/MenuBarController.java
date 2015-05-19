package titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import model.CreateException;
import model.ModelManager;

public class MenuBarController{
	
	public MenuBarController(MenuBar menu)
	{
		init(menu);
	}
	
	private void init(MenuBar menu)
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
			}
		});
	}
 
}
