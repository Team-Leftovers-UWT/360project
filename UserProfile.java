//mgashay
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UserProfile implements Serializable {

    private String name;

    private String email;

    private boolean isAdmin;

    public UserProfile(String name, String email, boolean isAdmin){
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return  name + " " + email;
    }

    public void Export(){
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("C:\\profile.txt");
            oos = new ObjectOutputStream(fout);
        }catch (Exception e){

        }
        finally {
            if(oos !=null){
                try {
                    oos.close();
                }catch(Exception e){

                }
            }
        }
    }
}
