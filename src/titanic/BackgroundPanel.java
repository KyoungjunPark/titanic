package titanic;

import java.awt.BorderLayout;

import javax.swing.*;

public class BackgroundPanel extends JFrame {
	
	protected JMenuBar menuBar;
	protected JMenu mnFile;
	protected JMenu mnMetrics;
	protected JMenu mnView;
	protected JMenu mnHelp;
	
	protected JMenuItem mnFileMenu;
	protected JMenuItem mnMetricsMenu;
	protected JMenuItem mnViewMenu;
	protected JMenuItem mnHelpMenu;
	
	protected JToolBar toolBar;

	
	
	public BackgroundPanel() {
		setTitle("Titanic");
		setSize(1400,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		makeMenus();
		makeOverallToolbar();
		makeMainPanel();
	
	}
	
	protected void makeMenus()
	{
		//Menus
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mnMetrics = new JMenu("Metrics");
		menuBar.add(mnMetrics);
		
		mnView = new JMenu("View");
		menuBar.add(mnView);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		
		
		mnFileMenu = new JMenuItem("Open DSM...");
		mnFile.add(mnFileMenu);
		
		mnFileMenu = new JMenuItem("New Clustering");
		mnFile.add(mnFileMenu);
		
		mnFileMenu = new JMenuItem("Load Clustering...");
		mnFile.add(mnFileMenu);
		
		mnFileMenu = new JMenuItem("Save Clustering");
		mnFile.add(mnFileMenu);
		
		mnFileMenu = new JMenuItem("Save Clustering...");
		mnFile.add(mnFileMenu);
		
		mnFileMenu = new JMenu("Export As");
		mnFile.add(mnFileMenu);
		
		mnFileMenu = new JMenuItem("Exit");
		mnFile.add(mnFileMenu);
		
		
		
		mnMetricsMenu = new JMenuItem("Propagation Cost");
		mnMetrics.add(mnMetricsMenu);
		
		
		
		mnViewMenu = new JMenuItem("Redraw");
		mnView.add(mnViewMenu);
		
		mnViewMenu = new JMenuItem("Find...");
		mnView.add(mnViewMenu);
		
		mnViewMenu = new JMenuItem("Show Row Labels");
		mnView.add(mnViewMenu);
		
		mnViewMenu = new JMenuItem("Show Dependency Strength");
		mnView.add(mnViewMenu);
		
		
		
		mnHelpMenu = new JMenuItem("About...");
		mnHelp.add(mnHelpMenu);
	}
	protected void makeOverallToolbar()
	{
		//overall ToolBar(in North)
		toolBar = new JToolBar();
		
		ImageIcon opendsmIcon = new ImageIcon(BackgroundPanel.class.getResource("").getPath()+"../../util/open-dsm.png");
		JButton opendsmButton = new JButton(opendsmIcon);
		toolBar.add(opendsmButton);

		ImageIcon redrawIcon = new ImageIcon(BackgroundPanel.class.getResource("").getPath()+"../../util/redraw.png");
		JButton redrawButton = new JButton(redrawIcon);
		toolBar.add(redrawButton);
		
		ImageIcon newClusteringIcon = new ImageIcon(BackgroundPanel.class.getResource("").getPath()+"../../util/new-clsx.png");
		JButton newClusteringButton = new JButton(newClusteringIcon);
		toolBar.add(newClusteringButton);
		
		ImageIcon loadClusteringIcon = new ImageIcon(BackgroundPanel.class.getResource("").getPath()+"../../util/open-clsx.png");
		JButton loadClusteringButton = new JButton(loadClusteringIcon);
		toolBar.add(loadClusteringButton);
		
		ImageIcon saveClusteringIcon = new ImageIcon(BackgroundPanel.class.getResource("").getPath()+"../../util/save-clsx.png");
		JButton saveClusteringButton = new JButton(saveClusteringIcon);
		toolBar.add(saveClusteringButton);
		
		ImageIcon saveClusteringAsIcon = new ImageIcon(BackgroundPanel.class.getResource("").getPath()+"../../util/save-clsx-as.png");
		JButton saveClusteringAsButton = new JButton(saveClusteringAsIcon);
		toolBar.add(saveClusteringAsButton);
		
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
	}
	protected void makeMainPanel()
	{
		//Main two screen - leftScreen, rightScreen
		MainPanel mainPanel = new MainPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BackgroundPanel().setVisible(true);
				}
			});
	}

}
