package titanic;

import java.awt.Event;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar {
	
	private JMenu mnFile;
	private JMenu mnMetrics;
	private JMenu mnView;
	private JMenu mnHelp;
	private JMenuItem openDSMItem;
	private JMenuItem loadClusteringItem;
	private JMenuItem saveClusteringItem;
	private JMenuItem saveClusteringAsItem;
	private JMenuItem exportAsItem;
	private JMenuItem exitActionItem;
	private JMenuItem propagationCostItem;
	
	public MenuBar()
	{
		//Menus
		
		mnFile = new JMenu("File");
		add(mnFile);
		mnFile.setMnemonic('F');
		
		mnMetrics = new JMenu("Metrics");
		add(mnMetrics);
		mnMetrics.setMnemonic('M');
		
		mnView = new JMenu("View");
		add(mnView);
		mnView.setMnemonic('V');
		
		mnHelp = new JMenu("Help");
		add(mnHelp);
		mnHelp.setMnemonic('H');

		
		openDSMItem = new JMenuItem("Open DSM...", new ImageIcon("util/open-dsm.png"));
        JMenuItem newClusteringItem = new JMenuItem("New Clustering", new ImageIcon("util/new-clsx.png"));
        newClusteringItem.setEnabled(false);
        loadClusteringItem = new JMenuItem("Load Clustering...", new ImageIcon("util/open-clsx.png"));
        loadClusteringItem.setEnabled(false);
        saveClusteringItem = new JMenuItem("Save Clustering", new ImageIcon("util/save-clsx.png"));
        saveClusteringItem.setEnabled(false);
        saveClusteringAsItem = new JMenuItem("Save Clustering...", new ImageIcon("util/save-clsx-as.png"));
        saveClusteringAsItem.setEnabled(false);
        exportAsItem = new JMenu("Export As");
        exitActionItem = new JMenuItem("Exit");
        propagationCostItem = new JMenuItem("Propagation Cost");
        propagationCostItem.setEnabled(false);
        
        JMenuItem redraw = new JMenuItem("Redraw", new ImageIcon("util/redraw.png"));
        redraw.setEnabled(false);
        JMenuItem find = new JMenuItem("Find...");
        find.setEnabled(false);
        JCheckBoxMenuItem showRowLabels = new JCheckBoxMenuItem("Show Row Labels");
        JCheckBoxMenuItem showDependencyStrength = new JCheckBoxMenuItem("Show Dependency Strength");
        
        JMenuItem about = new JMenuItem("About...");
        
        JMenuItem DSM = new JMenuItem("DSM...");
        DSM.setEnabled(false);
        JMenuItem excel = new JMenuItem("Excel...");
        excel.setEnabled(false);
        
        
        mnFile.add(openDSMItem);
        mnFile.addSeparator();
        mnFile.add(newClusteringItem);
        mnFile.add(loadClusteringItem);
        mnFile.addSeparator();
        mnFile.add(saveClusteringItem);
        mnFile.add(saveClusteringAsItem);
        mnFile.addSeparator();
        mnFile.add(exportAsItem);
        mnFile.addSeparator();
        mnFile.add(exitActionItem);
        openDSMItem.setMnemonic('O');
        newClusteringItem.setMnemonic('N');
        loadClusteringItem.setMnemonic('L');
        saveClusteringItem.setMnemonic('S');
        saveClusteringAsItem.setMnemonic('a');
        exportAsItem.setMnemonic('E');
        exitActionItem.setMnemonic('x');
        openDSMItem.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK, false));
        loadClusteringItem.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK + Event.SHIFT_MASK, false));
        saveClusteringItem.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK, false));
 
        exportAsItem.add(DSM);
        exportAsItem.add(excel);
        DSM.setMnemonic('D');
        excel.setMnemonic('E');
        
        mnMetrics.add(propagationCostItem);
        propagationCostItem.setMnemonic('P');
            
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
		
	}

	public JMenu getMnFile() {
		return mnFile;
	}

	public JMenu getMnMetrics() {
		return mnMetrics;
	}

	public JMenu getMnView() {
		return mnView;
	}

	public JMenu getMnHelp() {
		return mnHelp;
	}

	public JMenuItem getOpenDSMItem() {
		return openDSMItem;
	}

	public JMenuItem getLoadClusteringItem() {
		return loadClusteringItem;
	}

	public JMenuItem getSaveClusteringItem() {
		return saveClusteringItem;
	}

	public JMenuItem getSaveClusteringAsItem() {
		return saveClusteringAsItem;
	}

	public JMenuItem getExportAsItem() {
		return exportAsItem;
	}

	public JMenuItem getExitActionItem() {
		return exitActionItem;
	}

	public JMenuItem getPropagationCostItem() {
		return propagationCostItem;
	}
	
	
}
