/*
 *
 * @Author Masse
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Iteration2 extends JFrame{


    private final JList<File> fileList;
    private final DefaultListModel<File> listModel;
    private final JButton importButton;
    private final JButton exportButton;
    private final JButton deleteButton;
    private final JButton backButton;


    public Iteration2() {
        super("Import Export");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);

        // Set up list model and file list
        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set up buttons
        importButton = new JButton("Import");
        importButton.addActionListener(new importActionListener());
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteActionListener());
        exportButton = new JButton("Export");
        exportButton.addActionListener(new exportActionListener());
        backButton = new JButton("Back");
        backButton.addActionListener(new backActionListener());

        // Set up layout
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(importButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
        add(new JScrollPane(fileList), BorderLayout.CENTER);

       
        setVisible(true);
    }

    private class backActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            FoldersGUI.start();

        }
    }

    // Action listener for uploading a file
    private class importActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(Iteration2.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                listModel.addElement(selectedFile);
            }
        }
    }



    // Action listener for deleting a file
    private class DeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = fileList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        }
    }
    //Action listener for exporting a file
    private class exportActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = fileList.getSelectedIndex();
            if (selectedIndex != -1) {
                File selectedFile = listModel.get(selectedIndex);
                // Save the selected file
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File saveFile = fileChooser.getSelectedFile();

                }
            }
        }
    }


    public static void main(String[] args) {
        new Iteration2();
    }
}
