/**
 * @author kpc2000
 *
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; // Might not need.

public class Profile {
    private String username;
    private String email;
    private boolean admin;
    private int count;
    private Project[] projects;
    private File profilePic = null;

    /**
     * Parameterized Constructor for the Profile Class.
     * @param theName
     * @param theAdmin
     * @param theNum
     * @throws IOException
     */
    public Profile(String theName, String theEmail,  boolean theAdmin, int theNum) {
        this.username = theName;
        this.email = theEmail;
        this.admin = theAdmin;
        this.count = theNum;
    }

    /**
     * Retrieves Project Information under This Profile. Creates List of Projects.
     */
    public void buildProjects() {

    }

    /**
     * Returns Profile Administrative Status.
     * @return
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Returns Profile Username.
     * @return
     */
    public String getUserName() {
        return username;
    }

    /**
     * Returns Project list to Higher-Level Program.
     * @return
     */
    public Project[] getProjects() {
        return projects;
    }

    /**
     * Possible Return for Profile Pic to GUI / Display.
     * @return
     */
    public File getProfilePic() {
        return profilePic;
    }

    public String getEmail() { return email;
    }

    public int getCount() {
        return count;
    }

    /**
     * makeProfile method creates a .txt file containing the Username
     *            email and admin status of the account created.
     * @param theName is the user's username.
     * @param theEmail is the user's email
     * @param isAdmin is the admin status of the account
     * @param Count is the number of accounts listed in the program.
     */
    public static void makeProfile(String theName, String theEmail, boolean isAdmin, int Count) {
        try {
            String userFile = System.getProperty("user.home") + "\\Documents\\LeftOverApp\\Users\\" + theName + "\\Profile.txt";
            File Profile = new File (userFile);
            Profile.getParentFile().mkdirs();
            FileWriter profile = new FileWriter(userFile);
            profile.write(theName + "\n");
            profile.write(theEmail + "\n");
            profile.write("Admin status: " + isAdmin + "\n");
            profile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}