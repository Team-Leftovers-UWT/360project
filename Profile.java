/**
 * @author kpc2000
 *
 */

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; // Might not need.

public class Profile {
	private String username;
	private String email;
	private boolean admin;
	private int count; // Number of Users
	private Project[] projects;
	private File profilePic = null;
	
	/**
	 * Parameterized Constructor for the Profile Class.
	 * @param theName
	 * @param theEmail
	 * @param theAdmin
	 * @param theNum
	 * @throws IOException
	 */
	public Profile(String theName, String theEmail, boolean theAdmin, int theNum) throws IOException {
		String filePath = "/files/profilePicUser" + theNum; // EXAMPLE ONLY!
		this.username = theName;
		this.email = theEmail;
		this.admin = theAdmin;
		// Insert read command using custom filepath.
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
		return this.admin;
	}
	
	public static boolean setAdmin(boolean result) {
		return result;
	}
	
	/**
	 * Returns Profile Username.
	 * @return
	 */
	public String getUserName() {
		return this.username;
	}
	
	/**
	 * Returns Project list to Higher-Level Program.
	 * @return
	 */
	public Project[] getProjects() {
		return this.projects;
	}
	
	/**
	 * Possible Return for Profile Pic to GUI / Display.
	 * @return
	 */
	public File getProfilePic() {
		return this.profilePic;
	}
}
