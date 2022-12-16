/** 
 * This class hold the info displayed on the about page
 * and is called by the AppGUI class.
 *
 * @author Deep Singh, Kurtis Copeland
 */
public class VersionInfo {
	private final static String D1 = "Deep Singh";
	private final static String D2 = "Ho Jun Choi";
	private final static String D3 = "Masse Gashay";
	private final static String D4 = "Kurtis Copeland";
	private final static String D5 = "Kian Rivera";
	private final static String userName = "user";
	private final static String myGithub = "https://github.com/Team-Leftovers-UWT/360project";
	private final static String myVersion = "v.0.1.0";
	/**
	 * this method returns dev1
	 * @author Deep
	 */
	public static String getD1() {
		return D1;
	}
	/**
	 * this method returns dev2
	 * @author Deep
	 */
	public static String getD2() {
		return D2;
	}
	/**
	 * this method returns dev3
	 * @author Deep
	 */
	public static String getD3() {
		return D3;
	}
	/**
	 * this method returns dev4
	 * @author Deep
	 */
	public static String getD4() {
		return D4;
	}
	/**
	 * this method returns dev5
	 * @author Deep
	 */
	public static String getD5() {
		return D5;
	}
	/**
	 * this method returns our github link
	 * @author Deep
	 */
	public static String getGitHub() {
		return myGithub;
	}
	/**
	 * this method returns version of this app
	 * @author Deep
	 */
	public static String getVersion() {
		return myVersion;
	}
	/**
	 * this method returns the user's name
	 * @author Deep
	 */
	public static String getUser() {
		return userName;
	}
	/**
	 * this method returns all the devs in an array
	 * @author Kurtis
	 */
	public static String[] getDevs() {
		String[] output = new String[5];
		output[0] = D1;
		output[1] = D2;
		output[2] = D3;
		output[3] = D4;
		output[4] = D5;
		return output;
	}
}
