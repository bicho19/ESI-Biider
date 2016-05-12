package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;


public class Utils {



    public static String passHash(String passwordToHash){
        String hashtext = null;
        MessageDigest md = null;
        try {
            //Getting the md5 with salt hash
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(passwordToHash.getBytes());
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            hashtext= bigInt.toString(16);
// Now we need to zero pad it if you actually want the full 32 chars.
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }



        return hashtext;

    }

    //to check if the name contain only letters and spaces (valid name)
    public static boolean isNameValid(String s){
        //testing if "s" is !null & !empty & first character isn't space
        //!null and !empty so we don't fall in NullpointException when pointing to a character
        //first character isn't space because it must be a letter
        if ((s!=null) && (!s.isEmpty()) && s.charAt(0)!=' '){
            char [] tmp = s.toCharArray();//dicomposing the string into an char array
            //testing for each character if it's a letter or a space
            //if it is not a letter or space so the name isn't valid
            for (char c : tmp){
                if((!Character.isLetter(c)) && c!=' ') return false;
            }
            return true;//if we reach here that means all characters are either a letter or a space
        }
        if ((s==null) || (s.isEmpty())) return true; // if "s" is null or empty it's a valid name (in case of update user only !)
        return false; // if we get here that means first character is space so it's not valid name
    }

}
