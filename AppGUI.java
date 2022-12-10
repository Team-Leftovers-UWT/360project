import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class AppGUI extends JFrame {
    private static final Dimension FRAME_SIZE = new Dimension(550, 350);
    private JPanel panel;
    private JFrame frame;

    private JButton exportButton;
    private JButton importButton;
    private JButton aboutButton;
    private JButton profileButton;
    private JButton addProfile;
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
        System.out.println(" how many users are in the folder: " + userCount);
//        for (int i = 0; i < 4; i++) {
//            profileButton = new JButton(tryNames[i].getName());
//            tempPanel.add(profileButton);
//        }

        addProfile = new JButton("Create Profile");
        addProfile.setFocusable(false);

        aboutButton = new JButton("About");
        aboutButton.setFocusable(false);
        
        for (int i = 0; i < 4; i++) {
            profileButton = new JButton(tryNames[i].getName());
            tempPanel.add(profileButton);
        }

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

    private void addImportEvent() {
        importButton.addActionListener(e -> {
            System.out.println("Import button clicked.");
            fileChooser.setDialogTitle("File to Import");
            int userDestination = fileChooser.showOpenDialog(frame);
            if(userDestination == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + file.getAbsolutePath());
            }
        });
    }

    private void addExportEvent() {
        exportButton.addActionListener(e -> {
            System.out.println("Export button clicked.");
            fileChooser.setDialogTitle("File to Export");
            int userSelection = fileChooser.showSaveDialog(frame);
            if(userSelection == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                System.out.println("File Exported to: " + file.getAbsolutePath());
            }
        });
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

        if(userCount <= 4) {
	        submit.addActionListener(event -> {
	            userCount++;
	            //System.out.println("how many users are in the folder after clicking submit: " + userCount);
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
	            //userCount++;
	            
	            start();
	        });
        }
        else {
        	submit.addActionListener(event -> {
        		JFrame errorFrame = new JFrame("ERROR");
        		errorFrame.setVisible(true);
        		errorFrame.setLayout(null);
        		errorFrame.setSize(400,100);
        		errorFrame.setLocationRelativeTo(null);
        		
        		errorFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        		start();
        	});
        }
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
