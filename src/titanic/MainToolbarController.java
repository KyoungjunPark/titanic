package titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.sun.org.apache.xpath.internal.operations.Mod;

import model.SaveException;
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

                openDSMFile(openFile);

            }
        });

        toolbar.setAction("Redraw", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventManager.callEvent("Redraw-Table");

            }
        });
        toolbar.setAction("New Clustering", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected;
                if (ModelManager.sharedModelManager().getCurrentTitanicModel().isEdit()) {
                    selected = JOptionPane.showConfirmDialog(null, "Clustering has been modified, Save changes?", "Save changes?", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (selected == 0) { //yes
                        try {
                            ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().save();
                        } catch (SaveException e1) {
                            e1.printStackTrace();
                        }

                    } else if (selected == 1) { //no
                        //just pass
                    } else { // cancel
                        return;
                    }

                    ModelManager.sharedModelManager().getCurrentTitanicModel().removeClsxModel();
                    EventManager.callEvent("Redraw-Table");
                    EventManager.callEvent("Refresh-TabName");
                    EventManager.callEvent("Redraw-FileTree");

                }
            }

        });
        toolbar.setAction("Load Clustering", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File openFile;


                String dir = new String();

                dir += ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFilePath();
                int tmp = dir.lastIndexOf("\\");
                dir = dir.substring(0, tmp + 1);

                if (ModelManager.sharedModelManager().getCurrentTitanicModel().isEdit()) {
                    int selected = JOptionPane.showConfirmDialog(null, "Clustering has been modified, Save changes?", "Save changes?", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (selected == 0) { //yes
                        try {
                            ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().save();
                        } catch (SaveException e1) {
                            e1.printStackTrace();
                        }

                    } else if (selected == 1) { //no
                        //just pass
                    } else { // cancel
                        return;
                    }
                }

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
                try {
                    if(ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().getFilePath() == null){
                        File openFile;

                        String dir = new String();

                        dir += ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFilePath();
                        int tmp = dir.lastIndexOf("\\");
                        dir = dir.substring(0, tmp + 1);

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
                        int yn = fc.showSaveDialog(null);
                        if(yn != JFileChooser.APPROVE_OPTION) return;

                        openFile = fc.getSelectedFile();

                        ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().save(openFile.getPath());

                    }else{
                        ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().save();
                    }

                } catch (SaveException e1) {
                    e1.printStackTrace();
                }

            }
        });
        toolbar.setAction("Save Clustering As", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File openFile;

                String dir = new String();

                dir += ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().getFilePath();
                int tmp = dir.lastIndexOf("\\");
                dir = dir.substring(0, tmp + 1);

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
                int yn = fc.showSaveDialog(null);
                if(yn != JFileChooser.APPROVE_OPTION) return;

                openFile = fc.getSelectedFile();


                try {
                    ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().save(openFile.getPath());
                } catch (SaveException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    protected void changeDSMStatus() {
        toolbar.changeDSMStatus();

    }
}
