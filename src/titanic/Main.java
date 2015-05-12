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
		setLayout(new BorderLayout());
		
		
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
		
		
		//ToolBar
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		ImageIcon opendsmIcon = new ImageIcon(Main.class.getResource("").getPath()+"../../util/open-dsm.png");
		JButton opendsmButton = new JButton(opendsmIcon);
		toolBar.add(opendsmButton);

		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		
		//leftLayout
		JPanel panelLeft = new JPanel();
		panelLeft.add(new JTextField("Left"));
		getContentPane().add(panelLeft, BorderLayout.WEST);
		

		//RightLayout
		JPanel panelRight = new JPanel();
		panelRight.add(new JTextField("Right"));
		getContentPane().add(panelRight, BorderLayout.EAST);

	}
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Main().setVisible(true);
				}
			});
	}

}
