/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbytegt.usac_lib;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author JOSED
 */
public class Security {
    
    String encryptMD5(String key){
        try {
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(key.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext;
        } catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
        
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(key.getBytes());
//        byte[] digest = md.digest();
//        String hashMD5 = DatatypeConverter.printHexBinary(digest).toUpperCase();
//
//        return hashMD5;
        //assertThat(myHash.equals(hash)).isTrue();
    }
}
