package entities;

/**
 * Created by ZeroOne on 4/5/2016.
 */
public class Admin {
    private String username;
    private String password;

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public Admin(){}
    public Admin(String user, String pass){
        this.setUsername(user);
        this.setPassword(pass);
    }

}
