/** 
 * This class hold the info displayed on the about page
 * and is called by the AppGUI class.
 *
 * @author Deep Singh
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
	
	public static String getD1() {
		return D1;
	}
	public static String getD2() {
		return D2;
	}
	public static String getD3() {
		return D3;
	}
	public static String getD4() {
		return D4;
	}
	public static String getD5() {
		return D5;
	}
	public static String getGitHub() {
		return myGithub;
	}
	public static String getVersion() {
		return myVersion;
	}
	public static String getUser() {
		return userName;
	}
	
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
