import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AppGUI extends JFrame {
    private static final Dimension FRAME_SIZE = new Dimension(550, 350);
    private JPanel panel;
    private JFrame frame;

    private JButton exportButton;
    private JButton importButton;
    private JButton aboutButton;
    private File file = new File("."); // WE'RE GOING TO NEED A PATHNAME

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
        Path userDir = Paths.get(filePath + "\\Users");

        /**
         * // For each profile, create a profile button. -----> IMPORT PROFILES. MAYBE USE FOR LOOP FOR THIS SECTION
         *         profileButtonOrSomething.addActionListener(e -> {
         *             System.out.println("Profile button clicked.");
         *             mainPanel();
         *         });
         *         // tempPanel.add(profileButton); // FOR EACH PROFILE, ADD THE BUTTON TO TEMP FRAME
         *         // panel.add(tempPanel, BorderLayout.CENTER);
         */
        for (int i = 1; i < userCount + 1; i++) {
            profileButton = new JButton("User " + i);
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
        profileEvent();
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

        importButton = new JButton("Import");
        importButton.setFocusable(false);
        exportButton = new JButton("Export");
        exportButton.setFocusable(false);

        panel.add(importButton);
        panel.add(exportButton);

        frame.add(panel, BorderLayout.CENTER);
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
                System.out.println("Selected file: "+file.getAbsolutePath());
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
                System.out.println("File Exported to: "+file.getAbsolutePath());
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
            start();
        });
    }

    public static void main(String[] theArgs) {
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.home") + "\\Documents\\LeftOverApp\\Users"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        AppGUI theGUI = new AppGUI(); // Automatically starts.
    }

}
