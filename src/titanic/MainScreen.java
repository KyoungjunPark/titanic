package titanic;

import java.awt.BorderLayout;

import javax.swing.*;

	public class MainScreen extends JFrame {
	
	protected JSplitPane splitPane;
	
	protected JMenuBar menuBar;
	protected JMenu mnFile;
	protected JMenu mnNewMenu;
	protected JMenu mnMetrics;
	protected JMenu mnNewMenu_1;
	protected JMenu mnView;
	protected JMenu mnHelp;
	
	protected JToolBar toolBar;

	
	
	public MainScreen() {
		setTitle("Titanic");
		setSize(1400,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		makeMenus();
		makeOverallToolbar();
		makeTwoSplitScreen();
	
	}
	protected void makeMenus()
	{
		//Menus
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mnNewMenu = new JMenu("Open DSM...");
		mnFile.add(mnNewMenu);
		
		mnMetrics = new JMenu("Metrics");
		menuBar.add(mnMetrics);
		
		mnNewMenu_1 = new JMenu("Propagation Cost");
		mnMetrics.add(mnNewMenu_1);
		
		mnView = new JMenu("View");
		menuBar.add(mnView);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
	}
	protected void makeOverallToolbar()
	{
		//overall ToolBar(in North)
		toolBar = new JToolBar();
		
		ImageIcon opendsmIcon = new ImageIcon("util/open-dsm.png");
		JButton opendsmButton = new JButton(opendsmIcon);
		toolBar.add(opendsmButton);

		ImageIcon redrawIcon = new ImageIcon("util/redraw.png");
		JButton redrawButton = new JButton(redrawIcon);
		toolBar.add(redrawButton);
		
		ImageIcon newClusteringIcon = new ImageIcon("util/new-clsx.png");
		JButton newClusteringButton = new JButton(newClusteringIcon);
		toolBar.add(newClusteringButton);
		
		ImageIcon loadClusteringIcon = new ImageIcon("util/open-clsx.png");
		JButton loadClusteringButton = new JButton(loadClusteringIcon);
		toolBar.add(loadClusteringButton);
		
		ImageIcon saveClusteringIcon = new ImageIcon("util/save-clsx.png");
		JButton saveClusteringButton = new JButton(saveClusteringIcon);
		toolBar.add(saveClusteringButton);
		
		ImageIcon saveClusteringAsIcon = new ImageIcon("util/save-clsx-as.png");
		JButton saveClusteringAsButton = new JButton(saveClusteringAsIcon);
		toolBar.add(saveClusteringAsButton);
		
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
	}
	protected void makeTwoSplitScreen()
	{
		//Main two screen - leftScreen, rightScreen
		splitPane = new JSplitPane();
		
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		LeftScreen leftScreen = new LeftScreen();
		splitPane.setLeftComponent(leftScreen);
		
		RightScreen rightScreen = new RightScreen();
		splitPane.setRightComponent(rightScreen.getRightScreen());
		
	}
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainScreen().setVisible(true);
				}
			});
	}

}
