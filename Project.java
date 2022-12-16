/**
 * @author Kurtis
 */
import java.io.File;

public class Project {
	private String projectName;
	private File data;
	
	/**
	 * Parameterized Constructor for the Project Class.
	 * @param theName
	 */
	public Project(String theName) {
		this.projectName = theName;
	}
	
	/**
	 * Retrieves the Project Name.
	 * @return
	 */
	public String getProjectName() {
		return this.projectName;
	}
	
	/**
	 * Method to rename the Project.
	 * @param newName
	 */
	public void setProjectName(String newName) {
		this.projectName = newName;
	}
	
	/**
	 * Possible method to pull important information?
	 */
	public void retrieveData() {
		
	}
	
	/**
	 * Possible method to add more information, or perhaps an updated file?
	 */
	public void addData() {
		
	}
}
