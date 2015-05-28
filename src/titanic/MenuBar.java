package titanic;

import java.awt.Component;
import java.awt.Event;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class MenuBar extends JMenuBar implements Controllerable{
	
	private JMenu mnFile;
	private JMenu mnMetrics;
	private JMenu mnView;
	private JMenu mnHelp;
	
	private JMenuItem newDSMItem;
	private JMenuItem openDSMItem;
	private JMenuItem newClusteringItem;
	private JMenuItem loadClusteringItem;
	private JMenuItem saveClusteringItem;
	private JMenuItem saveClusteringAsItem;
	private JMenuItem saveDSMItem;
	private JMenuItem saveDSMAsItem;
	
	//private JMenuItem exportAsItem;
	private JMenuItem exitActionItem;
	private JMenuItem propagationCostItem;
	
	private JMenuItem redraw;
	private JMenuItem find;
	
	private JCheckBoxMenuItem showRowLabels;
	//private JCheckBoxMenuItem showDependencyStrength;
	
	private JMenuItem about;

	//private JMenuItem DSM;
	//private JMenuItem excel;
	
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

		newDSMItem = new JMenuItem("New DSM", new ImageIcon("util/new-clsx.png"));
		newDSMItem.setToolTipText("New DSM");
		newDSMItem.setEnabled(false);
		openDSMItem = new JMenuItem("Open DSM...", new ImageIcon("util/open-dsm.png"));
		openDSMItem.setToolTipText("Open DSM");
		newClusteringItem = new JMenuItem("New Clustering", new ImageIcon("util/new-clsx.png"));
		newClusteringItem.setToolTipText("New Clustering");
        newClusteringItem.setEnabled(false);
        loadClusteringItem = new JMenuItem("Load Clustering...", new ImageIcon("util/open-clsx.png"));
        loadClusteringItem.setToolTipText("Load Clustering");
        loadClusteringItem.setEnabled(false);
        saveClusteringItem = new JMenuItem("Save Clustering", new ImageIcon("util/save-clsx.png"));
        saveClusteringItem.setToolTipText("Save Clustering");
        saveClusteringItem.setEnabled(false);
        saveClusteringAsItem = new JMenuItem("Save Clustering...", new ImageIcon("util/save-clsx-as.png"));
        saveClusteringAsItem.setToolTipText("Save Clustering As");
        saveClusteringAsItem.setEnabled(false);
        saveDSMItem = new JMenuItem("Save DSM", new ImageIcon("util/save-clsx.png"));
        saveDSMItem.setToolTipText("Save DSM");
        saveDSMItem.setEnabled(false);
        saveDSMAsItem = new JMenuItem("Save DSM...", new ImageIcon("util/save-clsx-as.png"));
        saveDSMAsItem.setToolTipText("Save DSM As");
        saveDSMAsItem.setEnabled(false);
        exitActionItem = new JMenuItem("Exit");
        exitActionItem.setToolTipText("Exit");
        /*
         * export기능은 없고 save DSM과 save DSM As 기능이 있어 대체합니다.
         * exportAsItem = new JMenu("Export As");
         * exportAsItem.setToolTipText("Export As");
        */

        propagationCostItem = new JMenuItem("Propagation Cost");
        propagationCostItem.setToolTipText("Propagation Cost");
        propagationCostItem.setEnabled(false);
        
        redraw = new JMenuItem("Redraw", new ImageIcon("util/redraw.png"));
        redraw.setToolTipText("Redraw");
        redraw.setEnabled(false);
        find = new JMenuItem("Find...");
        find.setToolTipText("Find");
        find.setEnabled(false);
        showRowLabels = new JCheckBoxMenuItem("Show Row Labels");
        //showDependencyStrength = new JCheckBoxMenuItem("Show Dependency Strength");
        
        about = new JMenuItem("About...");
        about.setToolTipText("About");
        
        /*
        DSM = new JMenuItem("DSM...");
        DSM.setToolTipText("DSM");
        DSM.setEnabled(false);
        excel = new JMenuItem("Excel...");
        excel.setToolTipText("Excel");
        excel.setEnabled(false);
        */
        mnFile.add(newDSMItem);
        mnFile.add(openDSMItem);
        mnFile.addSeparator();
        mnFile.add(newClusteringItem);
        mnFile.add(loadClusteringItem);
        mnFile.addSeparator();
        mnFile.add(saveClusteringItem);
        mnFile.add(saveClusteringAsItem);
        mnFile.addSeparator();
        mnFile.add(saveDSMItem);
        mnFile.add(saveDSMAsItem);
        mnFile.addSeparator();
        /*
        mnFile.add(exportAsItem);
        mnFile.addSeparator();
        */
        mnFile.add(exitActionItem);
        
        openDSMItem.setMnemonic('O');
        newClusteringItem.setMnemonic('N');
        loadClusteringItem.setMnemonic('L');
        saveClusteringItem.setMnemonic('S');
        saveClusteringAsItem.setMnemonic('a');
        //exportAsItem.setMnemonic('E');
        //exitActionItem.setMnemonic('x');
        openDSMItem.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK, false));
        loadClusteringItem.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK + Event.SHIFT_MASK, false));
        saveClusteringItem.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK, false));
 
        /*
        exportAsItem.add(DSM);
        exportAsItem.add(excel);
        DSM.setMnemonic('D');
        excel.setMnemonic('E');
        */
        
        mnMetrics.add(propagationCostItem);
        propagationCostItem.setMnemonic('P');
            
        mnView.add(redraw);
        mnView.addSeparator();
        mnView.add(find);
        mnView.addSeparator();
        
        mnView.add(showRowLabels);
        //mnView.add(showDependencyStrength);
        
        redraw.setMnemonic('R');
        find.setMnemonic('F');
        showRowLabels.setMnemonic('L');
        //showDependencyStrength.setMnemonic('D');
        redraw.setAccelerator(KeyStroke.getKeyStroke(0, Event.F5, false));			//F5 ????? ???? ?????
        find.setAccelerator(KeyStroke.getKeyStroke('F', Event.CTRL_MASK, false));
        
        mnHelp.add(about);
        about.setMnemonic('A');

	}
	public boolean getShowRowLabelsState(){
		return showRowLabels.isSelected();
	}
	public void changeDSMStatus()
	{
        for(Component component : this.getComponents()){
        	for(Component item : ((JMenu)component).getMenuComponents()){
        		if( item instanceof JMenuItem)
	               ((JMenuItem) item).setEnabled(true);
        	}
        }
     propagationCostItem.setEnabled(false);
     find.setEnabled(false);
	}
	@Override
	public void setAction(String title, ActionListener action) {
        for(Component component : this.getComponents()){
        	for(Component item : ((JMenu)component).getMenuComponents()){
	            if( item instanceof JMenuItem && ((JMenuItem) item).getText().compareTo(title) == 0)
	                ((JMenuItem) item).addActionListener(action);
        	}
        }
		
	}

	
}