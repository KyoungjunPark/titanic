package titanic;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BackgroundPanel extends JFrame {
	
	protected JMenuBar menuBar;
	protected JMenu mnFile;
	protected JMenu mnMetrics;
	protected JMenu mnView;
	protected JMenu mnHelp;
	
	protected JToolBar toolBar;

	JTextArea text;  
	Container pane;
	
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
		mnFile.setMnemonic('F');
		
		mnMetrics = new JMenu("Metrics");
		menuBar.add(mnMetrics);
		mnMetrics.setMnemonic('M');
		
		mnView = new JMenu("View");
		menuBar.add(mnView);
		mnView.setMnemonic('V');
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		mnHelp.setMnemonic('H');

		
		JMenuItem openDSM = new JMenuItem("Open DSM...", new ImageIcon("util/open-dsm.png"));
        JMenuItem newClustering = new JMenuItem("New Clustering", new ImageIcon("util/new-clsx.png"));
        JMenuItem loadClustering = new JMenuItem("Load Clustering...", new ImageIcon("util/open-clsx.png"));
        JMenuItem saveClustering = new JMenuItem("Save Clustering", new ImageIcon("util/save-clsx.png"));
        JMenuItem saveClustering_2 = new JMenuItem("Save Clustering...", new ImageIcon("util/save-clsx-as.png"));
        JMenuItem exportAs = new JMenu("Export As");
        JMenuItem exitAction = new JMenuItem("Exit");
        
        JMenuItem propagationCost= new JMenuItem("Propagation Cost");
        
        JMenuItem redraw = new JMenuItem("Redraw", new ImageIcon("util/redraw.png"));
        JMenuItem find = new JMenuItem("Find...");
        JCheckBoxMenuItem showRowLabels = new JCheckBoxMenuItem("Show Row Labels");
        JCheckBoxMenuItem showDependencyStrength = new JCheckBoxMenuItem("Show Dependency Strength");
        
        JMenuItem about = new JMenuItem("About...");
        
        JMenuItem DSM = new JMenuItem("DSM...");
        JMenuItem excel = new JMenuItem("Excel...");
        
        
        mnFile.add(openDSM);
        mnFile.addSeparator();
        mnFile.add(newClustering);
        mnFile.add(loadClustering);
        mnFile.addSeparator();
        mnFile.add(saveClustering);
        mnFile.add(saveClustering_2);
        mnFile.addSeparator();
        mnFile.add(exportAs);
        mnFile.addSeparator();
        mnFile.add(exitAction);
        
        openDSM.setMnemonic('O');
        newClustering.setMnemonic('N');
        loadClustering.setMnemonic('L');
        saveClustering.setMnemonic('S');
        saveClustering_2.setMnemonic('a');
        exportAs.setMnemonic('E');
        exitAction.setMnemonic('x');
        openDSM.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK, false));
        loadClustering.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK + Event.SHIFT_MASK, false));
        saveClustering.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK, false));
 
        exportAs.add(DSM);
        exportAs.add(excel);
        DSM.setMnemonic('D');
        excel.setMnemonic('E');
        
        mnMetrics.add(propagationCost);
        propagationCost.setMnemonic('P');
        
        mnView.add(redraw);
        mnView.addSeparator();
        mnView.add(find);
        mnView.addSeparator();
        mnView.add(showRowLabels);
        mnView.add(showDependencyStrength);
        
        redraw.setMnemonic('R');
        find.setMnemonic('F');
        showRowLabels.setMnemonic('L');
        showDependencyStrength.setMnemonic('D');
        redraw.setAccelerator(KeyStroke.getKeyStroke(0, Event.F5, false));			//F5 단축키 설정 모르겠음
        find.setAccelerator(KeyStroke.getKeyStroke('F', Event.CTRL_MASK, false));
        
        mnHelp.add(about);
        about.setMnemonic('A');
        //frame.getContentPane(  ).add(menuBar, BorderLayout.SOUTH);
        
        
        about.addActionListener(new ActionListener()  								//불완전
        {  
         public void actionPerformed(ActionEvent e)  
         {  
        	 JPanel p1 = new JPanel();
    		 p1.setLayout(new GridLayout(3, 1));
    		 p1.add(new JLabel("Titan"));
    		 p1.add(new JLabel("version 1.0"));
    		 p1.add(new JLabel("Copyright(c) 2009-2015, Drexel University"));
         JOptionPane.showMessageDialog(pane, p1);
         }  
        });   
       
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
