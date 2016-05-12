package entities;

import java.sql.Date;
import java.util.ArrayList;


public class User implements  Comparable{
    private String id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String birthPlace;
    private char sex; // 'M' or 'F'
    private String address;
    private String fingerPrint; // path of the finger print image
    private ArrayList<String> photos = new ArrayList<>();  // array list of  paths of photos

    public User(){}

    public User(String nId, String lName , String fName,
                Date bd, String bp, char s, String adr, String fp, String photo){
        this.id=nId; this.lastName=lName; this.firstName=fName;
        this.birthDate=bd; this.birthPlace=bp; this.sex =s;
        this.address=adr; this.fingerPrint=fp; this.photos.add(photo);
    }

    public User(String nId, String lName , String fName,
                Date bd, String bp, char s, String adr, String fp, ArrayList<String> photos){
        this.id=nId; this.lastName=lName; this.firstName=fName;
        this.birthDate=bd; this.birthPlace=bp; this.sex =s;
        this.address=adr; this.fingerPrint=fp; this.photos=photos;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public Date getBirthDate() {return birthDate;}
    public void setBirthDate(Date birthDate) {this.birthDate = birthDate;}

    public String getBirthPlace() {return birthPlace;}
    public void setBirthPlace(String birthPlace) {this.birthPlace = birthPlace;}

    public char getSex() {
        return sex;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getFingerPrint() {return fingerPrint;}
    public void setFingerPrint(String path) {fingerPrint = path;}

    public ArrayList<String> getPhotos() {return photos;}
    public void setPhotos(ArrayList<String> photos) {this.photos = photos;}

    @Override
    public String toString(){
        return "ID : "+getId()+
                " | Last name : "+getLastName()+
                " | First name : "+getFirstName()+
                " | Date of birth : "+getBirthDate()+
                " | Place of birth : "+getBirthPlace()+
                " | Sex : "+getSex()+
                " | Address : "+getAddress()+
                " | Number of photos : "+getPhotos().size();
    }

    @Override
    public int compareTo(Object o){
        User u = (User)o;
        return this.id.compareTo(u.getId());
    }

}
