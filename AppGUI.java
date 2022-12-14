/**
 * This is main GUI class that actually runs the entire program
 * makes calls to all the other classes in the folder consisting of
 * Profile, VersionInfo, Iteration2, and FoldersGUI
 * 
 * @author Kurtis Copeland, Deep Singh, Messe Gashay, Kian Rivera
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AppGUI extends JFrame {
    private static final Dimension FRAME_SIZE = new Dimension(550, 350);
    private JPanel panel;
    private JFrame frame;
    private JButton aboutButton;
    private JButton profileButton;
    private JButton addProfile;
    private File[] tryNames = new File(System.getProperty("user.home") + "\\Documents\\LeftOverApp\\Users").listFiles();
    private int userCount = tryNames.length;

    /**
     * Parameterless Constructor
     */
    public AppGUI() {
        start();
    }

    /**
     * Start Method for the AppGUI
     * @author Kurtis 
     */
    private void start() {
        frame = new JFrame("Leftovers App");
        frame.setVisible(true);
        frame.setSize(FRAME_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profilePanel();

    }

    /**
     * Displays the About Button, Profiles from Documents Folder, and Create Profile Button.
     * @author Kian, Kurtis, Deep
     */
    private void profilePanel() {
        panel = new JPanel();
        panel.setSize(FRAME_SIZE);

        JPanel tempPanel = new JPanel();
        String[] theNames = pullNames();

        for (int i = 0; i < theNames.length; i++) {
            profileButton = new JButton(theNames[i]);
            tempPanel.add(profileButton);
            profileEvent();
        }

        addProfile = new JButton("Create Profile");
        addProfile.setFocusable(false);

        aboutButton = new JButton("About");
        aboutButton.setFocusable(false);

        panel.add(aboutButton, BorderLayout.SOUTH);
        tempPanel.add(addProfile, BorderLayout.NORTH);
        panel.add(tempPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);

        addProfileEvent();
        addAboutEvent();
    }

    /**
     * Displays the "About Us" Panel. Includes Developer Names, Version Number, and GitHub URL.
     * @author Deep, Kurtis
     */
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

    /**
     * Button ActionListener for the About Page
     * @author Kurtis
     */
    private void addAboutEvent() {
        aboutButton.addActionListener(e -> {
            System.out.println("About button clicked.");
            aboutPanel();
        });
    }

    /**
     * Button ActionListener for the Create Profile Page
     * @author Kurtis
     */
    private void addProfileEvent() {
        addProfile.addActionListener(event -> {
            System.out.println("Create Profile button clicked.");
            createProfilePanel();
        });
    }

    /**
     * createProfilePanel opens up a prompt asking the user for a
     * username, email and determine its admin status. If there are already 4 users,
     * prevent the user from creating more.
     * @author Kian, Deep, Kurtis
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

        if(userCount < 4) {
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
                tryNames = new File(System.getProperty("user.home") + "\\Documents\\LeftOverApp\\Users").listFiles();

	            start();
	        });
        }
        else {
        	submit.addActionListener(event -> {
        		JFrame errorFrame = new JFrame("ERROR");
        		errorFrame.setVisible(true);
        		errorFrame.setSize(400,100);
        		errorFrame.setLocationRelativeTo(null);
        		
        		errorFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                JLabel errorMessage = new JLabel();
                errorMessage.setLayout(new BorderLayout(2,1));
                errorMessage.setText("There are already 4 users.");

                errorFrame.add(errorMessage, BorderLayout.CENTER);


        	});
        }
    }

    /**
     * Button ActionListener to open the Project Categories (FoldersGUI).
     * @author Deep
     */
    private void profileEvent() {
        profileButton.addActionListener(e -> {
        	frame.dispose();
            FoldersGUI.start();
        });
    }

    /**
     * Pulls names of users from folder files into local string array
     * @author Kurtis
     */
    private String[] pullNames() {

        String[] output = new String[tryNames.length];
        for(int i = 0; i < tryNames.length; i++) {
            output[i] = tryNames[i].getName();
        }

        return output;
    }

    /**
     * Main driver class which starts the program.
     * It also creates the directory that the program will use to store data.
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
