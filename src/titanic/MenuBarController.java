package titanic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;




import model.CreateException;
import model.EventManager;
import model.ModelManager;

public class MenuBarController extends MainController{
	
	private MenuBar menu;
	
	public MenuBarController(MenuBar menu)
	{
		this.menu = menu;
		
		init();
	}
	
	private void init()
	{
		//File Menus
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

				//setting chaned when dsm file is open
				openDSMFile(openFile);
				
			}
		});
		menu.setAction("New Clustering", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ModelManager.sharedModelManager().getCurrentTitanicModel().removeClsxModel();
				EventManager.callEvent("Redraw");
				EventManager.callEvent("after-open-CLSX");
				
			}
		});
		menu.setAction("Load Clustering...", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File openFile;
				
				String dir = System.getProperty("user.dir");//this project's absolute path name
				
				if(ModelManager.sharedModelManager().getCurrentTitanicModel().isEdit()){
					int selected = JOptionPane.showConfirmDialog(null, "Clustering has been modified, Save changes?","Save changes?", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null);
					if(selected == 0){ //yes
						//save feature
					}else if(selected == 1){ //no
						//just pass
					}else{ // cancel
						return;
					}
				}
				
				JFileChooser fc = new JFileChooser(dir);
				fc.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return "CLSX Files";
					}
					
					@Override
					public boolean accept(File f) {
						// TODO Auto-generated method stub
						return f.getName().endsWith(".clsx") || f.isDirectory();
					}
				});
				int yn = fc.showOpenDialog(null);
				if(yn != JFileChooser.APPROVE_OPTION) return;
				
				openFile = fc.getSelectedFile();
				
				//setting chaned when clsx file is 
				OpenClsxStatus(openFile);

			}
		});	
		menu.setAction("Save Clustering", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(null, "Save Clustering is clicked");
				
			}
		});		
		menu.setAction("Save Clustering...", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(null, "Save Clustering... is clicked");
				
			}
		});
		menu.setAction("Save DSM", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(null, "Save DSM is clicked");
				
			}
		});		
		menu.setAction("Save DSM...", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(null, "Save DSM... is clicked");
				
			}
		});		
		menu.setAction("Exit", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(null, "Exit is clicked");
				
			}
		});		

		//Metrics Menu
		menu.setAction("Propagation Cost", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Propagation Cost is clicked");
				
			}
		});
	
		//View Menus
		menu.setAction("Redraw", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventManager.callEvent("Redraw");
				// 타이타닉 모델이 하나라도 생성되어 있다면 리드로우 해줌
				//if(ModelManager.sharedModelManager().getTitanicModelCount()!=0)
				//	EventManager.callEvent("Redraw");
			}
		});
		menu.setAction("Find...", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Find... is clicked");
			}
		});
		menu.setAction("Show Row Labels", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Show Row Labels is clicked");
				
			if(ModelManager.sharedModelManager().getTitanicModelCount()!=0)	
				EventManager.callEvent("Redraw");
				
			}
		});
		
		/* 구현x
		menu.setAction("Show Dependency Strength", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Show Dependency Strength is clicked");
				
			}
		});
	*/
		//Help Menu
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

	protected void changeDSMStatus()
	{
		menu.changeDSMStatus();
		
	}
}
