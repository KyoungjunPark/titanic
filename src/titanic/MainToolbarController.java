package titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import titanic.BackgroundPanel.MainToolbar;
import model.CreateException;
import model.EventManager;
import model.ModelManager;

public class MainToolbarController extends MainController {

	private MainToolbar toolbar;

	public MainToolbarController(MainToolbar toolbar) {
		this.toolbar = toolbar;

		init();
	}

	void init() {
		toolbar.setAction("Open DSM", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File openFile;

				String dir = System.getProperty("user.dir");// this project's
															// absolute path
															// name
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
				if (yn != JFileChooser.APPROVE_OPTION)
					return;

				openFile = fc.getSelectedFile();
				// when file open success, then available icon must be changed!
				// Then Manager must give a message like "It's ok".

				// setting chaned when dsm file is open
				// issue! setting값을 좀 더 구조적으로 배치할 수 없을까?
				openDSMFile(openFile);

			}
		});

		toolbar.setAction("Redraw", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(null, "Redraw is clicked");
				EventManager.callEvent("Redraw");

			}
		});
		toolbar.setAction("New Clustering", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(null, "New Clustering is clicked");

			}
		});
		toolbar.setAction("Load Clustering", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File openFile;

				String dir = System.getProperty("user.dir");// this project's
															// absolute path
															// name
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
				if (yn != JFileChooser.APPROVE_OPTION)
					return;

				openFile = fc.getSelectedFile();

				// setting chaned when clsx file is
				OpenClsxStatus(openFile);
			}
		});
		toolbar.setAction("Save Clustering", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Save Clustering is clicked");

			}
		});
		toolbar.setAction("Save Clustering As", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Save Clustering is clicked");
			}
		});
	}

	protected void changeDSMStatus() {
		toolbar.changeDSMStatus();

	}
}
