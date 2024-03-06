import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.awt.FileDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FileManager {
    private GUI gui;
    String fileName;
    String fileDirectory;

    public FileManager(GUI gui) {
        this.gui = gui;
    }

    public void newFile() {
        gui.textArea.setText("");
        gui.window.setTitle("New");
    }

    public void saveFile() {
        FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.LOAD);
    }

    public void openFile() {
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileDirectory = fd.getDirectory();
            gui.window.setTitle(fileName);
            gui.textArea.setText("");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileDirectory + fileName)))) {
                String line;
                while ((line = in.readLine()) != null) {
                    gui.textArea.append(line + '\n');
                }
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

    //переименовать
    public void saveAsFile() {
        FileDialog fd = new FileDialog(gui.window, "Save As", FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            File f = new File(fileName+fileDirectory);
            file.delete();
            fileName = fd.getFile();
            fileDirectory = fd.getDirectory();
            gui.window.setTitle(fileName);
            try (FileWriter fw = new FileWriter(fileDirectory + fileName)) {
                fw.write(gui.textArea.getText());
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
