package titanic;

import model.ModelManager;
import model.SaveException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class BackgroundPanelController extends MainController{

    private JFrame frame;

    public BackgroundPanelController(JFrame frame){
        this.frame = frame;

        init();
    }
    private void init()
    {
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (ModelManager.sharedModelManager().getCurrentTitanicModel() == null) {
                    System.exit(0);

                } else if (ModelManager.sharedModelManager().getCurrentTitanicModel().isEdit()) {
                    int selected = JOptionPane.showConfirmDialog(null, "Clustering has been modified, Save changes?", "Save changes?", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (selected == 0) { //yes
                        try {
                            if (ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().getFilePath() == null) {
                                File openFile;

                                String dir = "";
                                if (ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFilePath() == null) {
                                    dir = null;
                                } else {
                                    dir += ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFilePath();
                                    int tmp = dir.lastIndexOf("\\");
                                    dir = dir.substring(0, tmp + 1);
                                }
                                JFileChooser fc = new JFileChooser(dir);
                                fc.setFileFilter(new FileFilter() {

                                    @Override
                                    public String getDescription() {
                                        return "CLSX Files";
                                    }

                                    @Override
                                    public boolean accept(File f) {
                                        return f.getName().endsWith(".clsx") || f.isDirectory();
                                    }
                                });
                                int yn = fc.showSaveDialog(null);
                                if (yn != JFileChooser.APPROVE_OPTION) return;

                                openFile = fc.getSelectedFile();

                                if (!openFile.getName().endsWith(".clsx")) {
                                    String fileDir = openFile.getPath() + ".clsx";
                                    openFile = new File(fileDir);
                                }

                                try {
                                    ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().save(openFile.getPath());
                                } catch (SaveException e1) {
                                    e1.printStackTrace();
                                }
                            } else {
                                ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().save();
                            }
                        } catch (SaveException e1) {
                            e1.printStackTrace();
                        }
                        System.exit(0);
                    } else if (selected == 1) { //no
                        System.exit(0);
                    } else { // cancel
                        return;
                    }
                }
                System.exit(0);
            }
        });
    }
}
