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
    // подразумевается, что есть класс, который реализует GUI, и класс FileManager
    // используется для
    // взаимодействия с файлами и их содержимым через объект типа JTextArea

    public FileManager(GUI gui) {
        this.gui = gui;
    }

    public void newFile() {
        gui.textArea.setText("");
        gui.window.setTitle("New");

    }

    public void renameFile() {

    }

    public void openFile() {
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileDirectory = fd.getDirectory();
            gui.window.setTitle(fileName);
            gui.textArea.setText("");
        }
        System.out.println(fileName);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileDirectory + fileName)))) {
            String line;

            while ((line = in.readLine()) != null) {
                gui.textArea.append(line + '\n');
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        // if (result == JFileChooser.APPROVE_OPTION) {
        // File selectedFile = fileChooser.getSelectedFile();
        // String fileName = selectedFile.getName();
        // String filePath = selectedFile.getPath();
        // System.out.println(fileName + filePath);
        // try (BufferedReader in = new BufferedReader(new InputStreamReader(
        // new FileInputStream(fileName+filePath)))) {
        // String line;
        // while ((line = in.readLine()) != null) {
        // textArea.append(line);
        // }
        // }catch (Exception e) {
        // System.out.println(e.getStackTrace());
        // }
        // }
    }

    public void saveFile() {

    }
}