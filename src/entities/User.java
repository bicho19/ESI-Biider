package entities;

import java.util.ArrayList;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String birthPlace;
    private String address;
    private String fingerPrint;
    private ArrayList<String> photos = new ArrayList<>();

    public User(){}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getBirthDate() {return birthDate;}
    public void setBirthDate(String birthDate) {this.birthDate = birthDate;}

    public String getBirthPlace() {return birthPlace;}
    public void setBirthPlace(String birthPlace) {this.birthPlace = birthPlace;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getFingerPrint() {return fingerPrint;}
    public void setFingerPrint(String path) {fingerPrint = path;}

    public ArrayList<String> getPhotos() {return photos;}
    public void setPhotos(ArrayList<String> photos) {this.photos = photos;}

    @Override
    public String toString(){
        return "Name : "+getFirstName()+" "+getLastName()+ " "+photos.size();
    }

}
