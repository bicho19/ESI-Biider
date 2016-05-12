package utils;


import java.util.prefs.Preferences;

public class Sessions {
    private Preferences prefs;

    public Sessions(){
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    public String getUser() {return prefs.get("user",null);}

    public void setUser(String user) {prefs.put("user",user);}

    public boolean isLoggedIn() {return prefs.getBoolean("isLoggedIn",false);}

    public void setLoggedIn(boolean loggedIn) {prefs.putBoolean("isLoggedIn",loggedIn);}

    public void setPrefs(String user, boolean loggedIn, String type){
        prefs.put("user",user);
        prefs.putBoolean("isLoggedIn",loggedIn);
        prefs.put("accType",type);
    }

    public void setType(String type){prefs.put("accType",type);}

    public String getType(){return prefs.get("accType",null);}

    public void removeAll(){
        prefs.remove("user");
        prefs.remove("isLoggedIn");
        prefs.remove("accType");
    }
}
