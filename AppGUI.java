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
        JPanel tempPanel = new JPanel();

        /**
         * // For each profile, create a profile button. -----> IMPORT PROFILES. MAYBE USE FOR LOOP FOR THIS SECTION
         *         profileButtonOrSomething.addActionListener(e -> {
         *             System.out.println("Profile button clicked.");
         *             mainPanel();
         *         });
         *         // tempPanel.add(profileButton); // FOR EACH PROFILE, ADD THE BUTTON TO TEMP FRAME
         *         // panel.add(tempPanel, BorderLayout.CENTER);
         */


        aboutButton = new JButton("About");
        aboutButton.setFocusable(false);

        panel.add(aboutButton, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);
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
    
    /**
     * makeProfile method creates a .txt file containing the Username
     *            email and admin status of the account created.
     * @param theName is the user's username.
     * @param theEmail is the user's email
     * @param isAdmin is the admin status of the account
     * @param Count is the number of accounts listed in the program.
     */
    public static Profile[] readProfiles(int count) throws IOException {
    	
    	Profile[] theProfiles = new Profile[count];
    	String filePath = "./files/profiles.txt";
    	final Scanner theFile = new Scanner(new File(filePath));
		//final FileWriter outFile = new FileWriter(filePath); Save for writeProfiles
    	
    	int index = 0;
    	while(theFile.hasNext()) {
    		String theName = theFile.next();
    		String theEmail = theFile.next();
    		boolean theAdmin = theFile.nextBoolean();
    		
    		theProfiles[index] = new Profile(theName, theEmail, theAdmin, index);
    		index++;
    	}
    	
    	return theProfiles;
    }

    public static void main(String[] theArgs) {
        AppGUI theGUI = new AppGUI(); // Automatically starts.
    }

}
