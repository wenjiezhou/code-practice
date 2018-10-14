package com.apple.jay.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public enum Encryptor {
    
    INSTANCE();
    
    private IvParameterSpec iv;
    private SecretKeySpec skeySpec;
    private static final String AES = "AES";
    private static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5PADDING";
    private Encryptor() {
        try {
            //TODO: For a truly secure key, It need to be using a hardware security module (HSM) to generate and protect the key.
            final KeyGenerator keyGen = KeyGenerator.getInstance(AES);
            //128 bits ace key
            keyGen.init(128, new SecureRandom()); 
            SecretKey secretKey = keyGen.generateKey();
            
            //16 byte iv
            final byte[] initVector = new byte[16];
            final SecureRandom randomNumberGenerator = new SecureRandom();
            randomNumberGenerator.nextBytes(initVector);
            iv = new IvParameterSpec(initVector);
            skeySpec = new SecretKeySpec(secretKey.getEncoded(), AES);
        } catch (NoSuchAlgorithmException e) {
            // TODO exception handling is needed
            e.printStackTrace();
        } 
    }
    
    public String encrypt(String value) {

        Cipher cipher;
        try {
            cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
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
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
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