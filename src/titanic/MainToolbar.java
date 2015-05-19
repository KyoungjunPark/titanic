package titanic;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class MainToolbar extends JToolBar{
	
	private JButton opendsmButton;
	private JButton redrawButton;
	private JButton newClusteringButton;
	private JButton loadClusteringButton;
	private JButton saveClusteringButton;
	private JButton saveClusteringAsButton;
	
	public MainToolbar(){
		
		ImageIcon opendsmIcon = new ImageIcon("util/open-dsm.png");
		opendsmButton = new JButton(opendsmIcon);
		add(opendsmButton);
	
		ImageIcon redrawIcon = new ImageIcon("util/redraw.png");
		redrawButton = new JButton(redrawIcon);
		redrawButton.setEnabled(false);
		add(redrawButton);
		
		ImageIcon newClusteringIcon = new ImageIcon("util/new-clsx.png");
		newClusteringButton = new JButton(newClusteringIcon);
		newClusteringButton.setEnabled(false);
		add(newClusteringButton);
		
		ImageIcon loadClusteringIcon = new ImageIcon("util/open-clsx.png");
		loadClusteringButton = new JButton(loadClusteringIcon);
		loadClusteringButton.setEnabled(false);
		add(loadClusteringButton);
		
		ImageIcon saveClusteringIcon = new ImageIcon("util/save-clsx.png");
		saveClusteringButton = new JButton(saveClusteringIcon);
		saveClusteringButton.setEnabled(false);
		add(saveClusteringButton);
		
		ImageIcon saveClusteringAsIcon = new ImageIcon("util/save-clsx-as.png");
		saveClusteringAsButton = new JButton(saveClusteringAsIcon);
		saveClusteringAsButton.setEnabled(false);
		add(saveClusteringAsButton);
		
	}
	
	public JButton getOpendsmButton() {
		return opendsmButton;
	}

	public JButton getRedrawButton() {
		return redrawButton;
	}

	public JButton getNewClusteringButton() {
		return newClusteringButton;
	}

	public JButton getLoadClusteringButton() {
		return loadClusteringButton;
	}

	public JButton getSaveClusteringButton() {
		return saveClusteringButton;
	}

	public JButton getSaveClusteringAsButton() {
		return saveClusteringAsButton;
	}
}
