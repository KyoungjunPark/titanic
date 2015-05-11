package titanic;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.ByteOrder;

import javax.swing.BoxLayout;

public class Main extends JFrame {
	
	public Main() {
		setTitle("Titanic");
		setSize(1400,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		
		//Menus
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNewMenu = new JMenu("Open DSM...");
		mnFile.add(mnNewMenu);
		
		JMenu mnMetrics = new JMenu("Metrics");
		menuBar.add(mnMetrics);
		
		JMenu mnNewMenu_1 = new JMenu("Propagation Cost");
		mnMetrics.add(mnNewMenu_1);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		
		//overall ToolBar
		JToolBar toolBar = new JToolBar();
		
		ImageIcon opendsmIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/open-dsm.png");
		JButton opendsmButton = new JButton(opendsmIcon);
		toolBar.add(opendsmButton);

		ImageIcon redrawIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/redraw.png");
		JButton redrawButton = new JButton(redrawIcon);
		toolBar.add(redrawButton);
		
		ImageIcon newClusteringIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/new-clsx.png");
		JButton newClusteringButton = new JButton(newClusteringIcon);
		toolBar.add(newClusteringButton);
		
		ImageIcon loadClusteringIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/open-clsx.png");
		JButton loadClusteringButton = new JButton(loadClusteringIcon);
		toolBar.add(loadClusteringButton);
		
		ImageIcon saveClusteringIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/save-clsx.png");
		JButton saveClusteringButton = new JButton(saveClusteringIcon);
		toolBar.add(saveClusteringButton);
		
		ImageIcon saveClusteringAsIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/save-clsx-as.png");
		JButton saveClusteringAsButton = new JButton(saveClusteringAsIcon);
		toolBar.add(saveClusteringAsButton);
		
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		
		//Main two screen - leftScreen, rightScreen
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		
		//for leftScreen
		JPanel leftScreen = new JPanel(new BorderLayout());
		
		
		//left side ToolBar
		JToolBar toolBar2 = new JToolBar();
		toolBar2.setFloatable(false);
		
		ImageIcon expandAllIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/expand.png");
		JButton expandAllButton = new JButton(expandAllIcon);
		toolBar2.add(expandAllButton);
		
		ImageIcon collapseAllIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/collapse.png");
		JButton collapseAllButton = new JButton(collapseAllIcon);
		toolBar2.add(collapseAllButton);
		
		ImageIcon groupIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/group.png");
		JButton groupButton = new JButton(groupIcon);
		toolBar2.add(groupButton);
		
		ImageIcon ungroupIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/ungroup.png");
		JButton ungroupButton = new JButton(ungroupIcon);
		toolBar2.add(ungroupButton);
		
		ImageIcon moveUpIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/up.png");
		JButton moveUpButton = new JButton(moveUpIcon);
		toolBar2.add(moveUpButton);
		
		ImageIcon moveDownIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/down.png");
		JButton moveDownButton = new JButton(moveDownIcon);
		toolBar2.add(moveDownButton);
		
		ImageIcon deleteIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/delete.png");
		JButton deleteButton = new JButton(deleteIcon);
		toolBar2.add(deleteButton);
		
		leftScreen.add(toolBar2, BorderLayout.NORTH);
		
		splitPane.setLeftComponent(leftScreen);

		
		
		

	}
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Main().setVisible(true);
				}
			});
	}

}
