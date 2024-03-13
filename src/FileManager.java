package FileManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.awt.FileDialog;

public class FileManager {
    private GUI gui;
    String fileName;
    String fileDirectory;
    // подразумевается, что есть класс, который реализует GUI

    public FileManager(GUI gui) {
        this.gui = gui;
        fileName = null;
        fileDirectory = null;
    }

    public void newFile() {
        gui.textArea.setText("");
        fileName = null;
        fileDirectory = null;
    }

    public void saveAsFile() {
        FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileDirectory = fd.getDirectory();
            try (FileWriter fw = new FileWriter(fileDirectory + fileName)) {
                fw.write(gui.textArea.getText());
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

    public void renameFile() {
        FileDialog fd = new FileDialog(gui.window, "Rename", FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            if (fileName == null) {
                saveAsFile();
            } else {
                String oldName = fileDirectory + fileName;
                fileName = fd.getFile();
                fileDirectory = fd.getDirectory();
                String newName = fileDirectory + fileName;
                File file = new File(oldName);
                File newFile = new File(newName);
                if (file.exists()) {
                    if (file.renameTo(newFile)) {
                        System.out.println("File renamed");
                    } else {
                        System.out.println("Couldn`t rename file");
                    }
                } else {
                    System.out.println("File doesn`t exist");
                }
            }
        }
    }

    public void openFile() {
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileDirectory = fd.getDirectory();
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

    public void saveFile() {
        if (fileName == null) {
            saveAsFile();
        } else {
            try (FileWriter fw = new FileWriter(fileDirectory + fileName)) {
                fw.write(gui.textArea.getText());
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
