// package Iteration2;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;


public class AppGUI extends JFrame {
    private static final Dimension FRAME_SIZE = new Dimension(550, 350);
    private JPanel panel;
    private JFrame frame;

    private JButton aboutButton;
    private JButton profileButton;
    private JButton addProfile;
    
    private JButton exportButton;
    private JButton importButton;
    private JButton deleteButton;
    private JList<File> fileList;
    private ArrayList<File> arrayFiles;
    private JPanel utilityPanel;
    
    private String filePath = System.getProperty("user.home") + "\\Documents\\LeftOverApp";
    private File file = new File(System.getProperty("user.home") + "\\Documents\\LeftOverApp");
    private int userCount = new File(System.getProperty("user.home") + "\\Documents\\LeftOverApp\\Users").list().length;
    private File[] tryNames = new File(System.getProperty("user.home") + "\\Documents\\LeftOverApp\\Users").listFiles();
    private final JFileChooser fileChooser = new JFileChooser();

    public AppGUI() {
        start();
    }

    private void start() {
        frame = new JFrame("Leftovers App");
        frame.setVisible(true);
        frame.setSize(FRAME_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profilePanel();
       

    }

    private void profilePanel() {
        panel = new JPanel();
        panel.setSize(FRAME_SIZE);

        JPanel tempPanel = new JPanel();

        for (int i = 1; i < userCount + 1; i++) {
            profileButton = new JButton(tryNames[i].getName());
            tempPanel.add(profileButton);
        }

        addProfile = new JButton("Create Profile");
        addProfile.setFocusable(false);

        aboutButton = new JButton("About");
        aboutButton.setFocusable(false);

        panel.add(aboutButton, BorderLayout.SOUTH);
        tempPanel.add(addProfile, BorderLayout.NORTH);
        panel.add(tempPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);
        //profileEvent();
        addProfileEvent();
        addAboutEvent();
    }

    private void aboutPanel() {
        JFrame aboutFrame = new JFrame("About the Leftovers App"); // Creates a New Window for About Info.
        aboutFrame.setVisible(true);
        aboutFrame.setSize(FRAME_SIZE);
        aboutFrame.setLocationRelativeTo(null);
        aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();

        JPanel userPanel = new JPanel();
        JPanel teamPanel = new JPanel();
        JPanel versionPanel = new JPanel();

        userPanel.add(new JLabel("This app is registered to: " + VersionInfo.getUser(), JLabel.CENTER));
        userPanel.add(new JLabel(" |  This app is provided by:", JLabel.CENTER));

        teamPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        teamPanel.setLayout(new GridLayout(9, 1));
        teamPanel.add(new JLabel(VersionInfo.getGitHub(), JLabel.CENTER));
        teamPanel.add(new JLabel(""));
        String[] tempList = VersionInfo.getDevs();
        for (String s : tempList) {
            teamPanel.add(new JLabel(s, JLabel.CENTER));
        }

        versionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        versionPanel.add(new JLabel("Version: " + VersionInfo.getVersion()));

        panel.add(userPanel, BorderLayout.NORTH);
        panel.add(teamPanel, BorderLayout.CENTER);
        panel.add(versionPanel, BorderLayout.SOUTH);

        aboutFrame.add(panel, BorderLayout.CENTER);

    }
    // add utilities method to shorten this method?
    // change needed
    private void mainPanel() {
        panel = new JPanel();
        panel.setSize(FRAME_SIZE);
        panel.setVisible(true);
        panel.setLayout(null);
        frame.setContentPane(panel);

        Path userPath = Paths.get(System.getProperty("user.home") + "\\Documents\\LeftOverApp\\Users");
        Path testPath = userPath.getName(0);
        System.out.println(testPath);

        try {
            Scanner profileDetails = new Scanner(new File(System.getProperty("user.home") + "\\Documents\\LeftOverApp\\Users"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        importButton = new JButton("Import");
        importButton.setBounds(350, 250,  75, 30);
        importButton.setFocusable(false);
        exportButton = new JButton("Export");
        exportButton.setBounds(450, 250,  75, 30);
        exportButton.setFocusable(false);

        panel.add(importButton);
        panel.add(exportButton);

        addImportEvent();
        addExportEvent();
    }

    private void addAboutEvent() {
        aboutButton.addActionListener(e -> {
            System.out.println("About button clicked.");
            aboutPanel();
        });
    }
    
    /* 
     * creates new panel within the frame 
     * provides the option for user to import/export/delete files
     *
     *
     * not added to the main frame, need a button to let the user enter this options
     */  
    private void utilities(){
        frame = new JFrame("Utilities");
        utilityPanel = new JPanel();
        utilityPanel.setLayout(new BorderLayout());
        frame.add(utilityPanel);
        fileList = new JList<>();
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        utilityPanel.add( new JScrollPane(fileList), BorderLayout.CENTER);
        importButton = new JButton("Import");
        exportButton = new JButton("Export");
        deleteButton = new JButton("Delete");
        
        importButton.addActionListener(e -> {
            System.out.println("Import button clicked.");
            fileChooser.setMultiSelectionEnabled(true);
            int returnValue = fileChooser.showOpenDialog(null);
            if(returnValue == fileChooser.APPROVE_OPTION){
               File[] selectedFiles = fileChooser.getSelectedFiles();
                for(File f : selectedFiles){
                    arrayFiles.add(f);
                }
                updateFileList();
            }                               
       });
        exportButton.addActionListener(e -> {
            int selectedIndex = fileList.getSelectedIndex();
            if(selectedIndex != -1){
                File SelectFile = arrayFiles.get(selectedIndex);
                updateFileList();
            }
        });
      deleteButton.addActionListener(e -> {
          int seleIndex = fileList.getSelectedIndex();
          if(seleIndex != -1){
            arrayFiles.remove(seleIndex);
              updateFileList();
          }
      });
    JPanel buttonPanel = new JPanel();
        buttonPanel.add(importButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(deleteButton);
        utilityPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /*
     * helper Method to update files for utilities.
     */  
     private void updateFileList() {
         File[] fileArray = new File[arrayFiles.size()];
         fileArray = arrayFiles.toArray(fileArray);
    }
    

    private void addProfileEvent() {
        addProfile.addActionListener(event -> {
            System.out.println("Create Profile button clicked.");
            createProfilePanel();
        });
    }

    /**
     * createProfilePanel opens up a prompt asking the user for a
     *                      username, email and determine its admin status
     */
    private void createProfilePanel() {
        JFrame createFrame = new JFrame("Create a Profile"); // Creates a New Window for About Info.
        createFrame.setVisible(true);
        createFrame.setLayout(null);
        createFrame.setSize(300,180);
        createFrame.setLocationRelativeTo(null);

        JTextField setUsername = new JTextField();
        setUsername.setBounds(100,10,165,25);

        JLabel user = new JLabel("Username");
        user.setBounds(10,10, 80,25);

        JTextField setEmail = new JTextField();
        setEmail.setBounds(100,50,165,25);

        JLabel userEmail = new JLabel("Email");
        userEmail.setBounds(10, 50, 80, 25);

        JCheckBox isAdmin = new JCheckBox();
        isAdmin.setText("Admin Account");
        isAdmin.setBounds(80, 80, 150, 25);
        isAdmin.setFocusable(false);

        JButton submit = new JButton("Submit");
        submit.setBounds(80,110,150,25);

        createFrame.add(user);
        createFrame.add(setUsername);
        createFrame.add(userEmail);
        createFrame.add(setEmail);
        createFrame.add(isAdmin);
        createFrame.add(submit);

        submit.addActionListener(event -> {
            userCount++;
            String username = setUsername.getText();
            String email = setEmail.getText();
            boolean adminStatus = false;
            if (isAdmin.isSelected()) {
                adminStatus = true;
            }
            Profile User = new Profile(username, email, adminStatus, userCount);
            Profile.makeProfile(User.getUserName(), User.getEmail(), User.isAdmin(),User.getCount());
            createFrame.dispose();
            frame.dispose();
            
            // Updates userCount
            userCount++;
            
            start();
        });
    }

    private void profileEvent() {
        profileButton.addActionListener(e -> {
            //mainPanel();
        });
    }

    /**
     * Main driver class which starts the program.
     * It also creates the directory that the program will use to store data.
     * @param theArgs
     */
    public static void main(String[] theArgs) {
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.home") + "\\Documents\\LeftOverApp\\Users"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppGUI theGUI = new AppGUI(); // Automatically starts.
    }

}
