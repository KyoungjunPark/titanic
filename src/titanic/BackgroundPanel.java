package titanic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class BackgroundPanel extends JFrame implements Controllerable{
	
	private MainToolbar toolbar;
	private MenuBar menubar;
	private CenterPanel mainPanel;
	
	public BackgroundPanel()
	{
		setTitle("Titanic");
		setSize(1000,700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		//add Toolbar
		toolbar = new MainToolbar();
		getContentPane().add(toolbar, BorderLayout.NORTH);
			
		//add Menus
		menubar = new MenuBar();
		setJMenuBar(menubar);
		
		//add MainPanel
		mainPanel = new CenterPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	
		setControllers();
	}
	public void setControllers()
	{
        BackgroundPanelController backgroundPanelController = new BackgroundPanelController(this);
		MainController mainController = new MainController(toolbar, menubar, mainPanel);
		
	
	}
	@Override
	public void setAction(String title, ActionListener action) {
		// TODO Auto-generated method stub
		
	}
	
	public class MainToolbar extends JToolBar implements Controllerable{
		
		private JButton opendsmButton;
		private JButton redrawButton;
		private JButton newClusteringButton;
		private JButton loadClusteringButton;
		private JButton saveClusteringButton;
		private JButton saveClusteringAsButton;
		
		public MainToolbar(){
			
			ImageIcon opendsmIcon = new ImageIcon("util/open-dsm.png");
			opendsmButton = new JButton(opendsmIcon);
			opendsmButton.setName("Open DSM");
			opendsmButton.setToolTipText("Open DSM");
			add(opendsmButton);
		
			ImageIcon redrawIcon = new ImageIcon("util/redraw.png");
			redrawButton = new JButton(redrawIcon);
			redrawButton.setEnabled(false);
			redrawButton.setName("Redraw");
			redrawButton.setToolTipText("Redraw");
			add(redrawButton);
			
			ImageIcon newClusteringIcon = new ImageIcon("util/new-clsx.png");
			newClusteringButton = new JButton(newClusteringIcon);
			newClusteringButton.setEnabled(false);
			newClusteringButton.setName("New Clustering");
			newClusteringButton.setToolTipText("New Clustering");
			add(newClusteringButton);
			
			ImageIcon loadClusteringIcon = new ImageIcon("util/open-clsx.png");
			loadClusteringButton = new JButton(loadClusteringIcon);
			loadClusteringButton.setEnabled(false);
			loadClusteringButton.setName("Load Clustering");
			loadClusteringButton.setToolTipText("Load Clustering");
			add(loadClusteringButton);
			
			ImageIcon saveClusteringIcon = new ImageIcon("util/save-clsx.png");
			saveClusteringButton = new JButton(saveClusteringIcon);
			saveClusteringButton.setEnabled(false);
			saveClusteringButton.setName("Save Clustering");
			saveClusteringButton.setToolTipText("Save Clustering");
			add(saveClusteringButton);
			
			ImageIcon saveClusteringAsIcon = new ImageIcon("util/save-clsx-as.png");
			saveClusteringAsButton = new JButton(saveClusteringAsIcon);
			saveClusteringAsButton.setEnabled(false);
			saveClusteringAsButton.setName("Save Clustering As");
			saveClusteringAsButton.setToolTipText("Save Clustering As");
			add(saveClusteringAsButton);
			
			
		}
		public void changeDSMStatus()
		{

	        for(Component component : this.getComponents()){
		            if(component instanceof JButton)
		                ((JButton) component).setEnabled(true);
		    }
	    }
		public void changeEditStatus()
		{
            newClusteringButton.setEnabled(false);
            loadClusteringButton.setEnabled(false);
            saveClusteringAsButton.setEnabled(false);
            saveClusteringButton.setEnabled(false);

		}
		public void changeInitialStatus(){
           opendsmButton.setEnabled(true);
            redrawButton.setEnabled(false);
            newClusteringButton.setEnabled(false);
            loadClusteringButton.setEnabled(false);
            saveClusteringButton.setEnabled(false);
            saveClusteringAsButton.setEnabled(false);
            
        }
		@Override
		public void setAction(String title, ActionListener action) {
	        for(Component component : this.getComponents()){
	        	if(component instanceof JButton && ((JButton) component).getName().compareTo(title) == 0)
	        		((JButton) component).addActionListener(action);  	
	        }
			
		}
	}
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BackgroundPanel().setVisible(true);
				}
			});
	}


}
