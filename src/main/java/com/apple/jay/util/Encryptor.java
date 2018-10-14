package com.apple.jay.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public enum Encryptor {
    
    INSTANCE("Bar12345Bar12345", "RandomInitVector");
    
//    String key = "Bar12345Bar12345"; // 128 bit key
//    String initVector = "RandomInitVector"; // 16 bytes IV
    
    IvParameterSpec iv;
    SecretKeySpec skeySpec;
    private Encryptor(String key, String initVector) {
        try {
            iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        } catch (UnsupportedEncodingException e) {
            // TODO exception handling is needed
            e.printStackTrace();
        }
    }
    
    public String encrypt(String value) {

        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted;
            encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            // TODO exception handling is needed
            e.printStackTrace();
        } 
        return null;
    }

    public String decrypt(String encrypted) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            // TODO exception handling is needed
            ex.printStackTrace();
        }
        return null;
    }
}