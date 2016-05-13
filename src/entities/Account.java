package entities;


public class Account {
    private String username;
    private String password;
    private String accType;

    public Account(){}
    public Account(String user, String pass, String type){
        username = user;
        password = pass;
        accType = type;
    }

    public Account(String user, String pass){
        username = user;
        password = pass;

    }


    @Override
    public String toString (){
        return "Username : "+username+" | Password : ******"+" | Account type : "+accType;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getAccType() {
        return accType;
    }

    public void setAccType(String type) {
        this.accType = type;
    }
}
